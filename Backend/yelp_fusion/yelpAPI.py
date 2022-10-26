import requests
from bs4 import BeautifulSoup
from urllib.parse import urlparse

import pandas as pd
import datetime
import time
import sqlalchemy

db_u = 'cyrate'
db_p = 'password'
db_host = 'coms-309-020.class.las.iastate.edu'
db_name = 'cy_rate'
conn = sqlalchemy.create_engine(f"mysql+mysqlconnector://{db_u}:{db_p}@{db_host}/{db_name}")

# Tries to find /menu yelp link
# if not found it calls google(rest)
def find_menu(url: str, rest: str) -> str:
    link = try_yelp_menu(url)
    if  link == "":
        link = google(rest)
    return link
    
# returns most frequent link that appears in google search
def google(rest: str) -> str:
    # Get all links from google search using {rest}
    response = requests.get(f'https://www.google.com/search?q={rest}')
    soup = BeautifulSoup(response.text, "html.parser")
    links = soup.find_all('a')
    # empty list so we can calculate most freq
    results = []
    for a in links:
        # finds only urls that arent related to google search elements
        if '/url?q' in a['href']:
            linkHref = a['href']
            linkHref = linkHref.split('q=')[1]
            # Parses Url into object
            # can access just 'www.whateversite.com' w/ .netloc
            s = urlparse(linkHref).netloc
            results.append(s)
    mostFreq = max(set(results), key=results.count)
    return mostFreq


# returns yelp /menu link if found
def try_yelp_menu(url: str):
    # Example url =
    # 'https://www.yelp.com/biz/cornbred-ames?adjust_creative=Jzh_gvi4YouhU5-CrA6Z8A&utm_
    #           campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=Jzh_gvi4YouhU5-CrA6Z8A'
    #
    # after split 1
    # ['https://www.yelp.com/', 'cornbred-ames?adjust_creative=Jzh_gvi4YouhU5-CrA6Z8A&utm_ campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=Jzh_gvi4YouhU5-CrA6Z8A']
    # after split 2
    # ['/cornbred-ames', 'adjust_creative=Jzh_gvi4YouhU5-CrA6Z8A&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=Jzh_gvi4YouhU5-CrA6Z8A']
    # name = '/cornbred-ames'     
    name = url.split('/biz')[1]
    name = name.split('?')[0]
    search = 'https://www.yelp.com/menu' + name
    try:
        response = requests.get(search)
        # if redirected wont equal search, return empty
        if response.url != search:
            return ""
        return search
    except:
        # sleep to avoid another timeout
        # try again
        time.sleep(2)
        s = try_yelp_menu(url)
        return s
    
    

# returns all results from business search location = "ames" from yelp_api
# need to return hours from yelp 'Business Details'
def main():
    headers = {"Authorization": "Bearer w_IaAch4CRD-Mb70s8AHJz4X_PQGUuGwZIvi9vXhGyL7_jTut-4Bgr5A61QiS8vtnryhEvCAyQUNhIRvB29yV2l_xAElHlq_Y946nRxJMlhPCC5JRBlbSr3w2xUyY3Yx"}
    url = 'https://api.yelp.com/v3/businesses/search?location=Ames&radius=40000&limit=50'
    data = requests.get(url, headers=headers)
    data = data.json()
    businesses = data.get('businesses')
    bus_uni = []
    # days are ordered in json object as 0 1 2 3 4 5 6
    # using this to create hours str below
    days_dic = {0:"Mon", 1:"Tue", 2:"Wed",3:"Thu",4:"Fri",5:"Sat", 6:"Sun"}

    # for each business in json response
    for element in businesses:
        time.sleep(1) # sleep between each iteration to not freak out yelp
        print(f"Starting {element.get('name')}")
        print(f'\tGathering hours...')
        
        # using yelp business details api for retrieving hours
        # using yelps business id in a f string
        url = f"https://api.yelp.com/v3/businesses/{element.get('id')}"
        response = requests.get(url, headers=headers)
        response = response.json()
        # skip to next iteration if there is a problem w response
        if response.get('hours') == None:
            continue
        print(f'\tHours Recieved')
        
        # navigate to hours in jsop response
        week = response.get('hours')[0].get('open')
        # For each day in the week
        hours_str = ""
        for day in week:
            
            # get open and close time for each day
            # convert to standard time w/ AM/PM
            # append to hours_str
            # hours_str format -> "Mon: 12:00PM - 4:00PM | Tue ...."
            start = day.get('start')
            end = day.get('end')
            start = datetime.datetime.strptime(start, '%H%M').strftime('%I:%M %p')
            end = datetime.datetime.strptime(end, '%H%M').strftime('%I:%M %p')
            hours_str += f" {days_dic.get(day.get('day'))}: {start} - {end} |"

        # Create Business Obj basically
        bus = {
        'bus_name': element.get('name'),
        'bus_type': element.get('categories')[0].get('title'), 
        'location': f"{element.get('location').get('address1')}, {element.get('location').get('city')} ({element.get('location').get('zip_code')})",
        'menu_link': find_menu(element.get('url'), element.get('name')),
        'owner_id': -1,
        'hours': hours_str,
        'photo_url': element.get('image_url'),
        'price_gauge': element.get('price'),
        'review_count': 0,
        'review_sum': 0,
        }

        bus_uni.append(bus)
        print(f"{element.get('name')} added to bus_uni\n")

    # Create pandas dataframe using bus_uni
    df = pd.DataFrame(data=bus_uni)
    # rename index to match sql table
    df.index.names = ['bus_id']
    df.to_sql(con=conn, name='business', if_exists='replace')
    print('Success: Business table updated')
    return

if __name__ == '__main__':
    main()





