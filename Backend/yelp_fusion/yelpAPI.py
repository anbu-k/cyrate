import requests
import json
import pandas as pd

# returns all results from business search location = "ames" from yelp_api
# need to return hours from yelp 'Business Details's
def main():
    headers = {"Authorization": "Bearer w_IaAch4CRD-Mb70s8AHJz4X_PQGUuGwZIvi9vXhGyL7_jTut-4Bgr5A61QiS8vtnryhEvCAyQUNhIRvB29yV2l_xAElHlq_Y946nRxJMlhPCC5JRBlbSr3w2xUyY3Yx"}
    url = 'https://api.yelp.com/v3/businesses/search?location=Ames&radius=40000&limit=50'
    data = requests.get(url, headers=headers)
    data = data.json()
    businesses = data.get('businesses')
    bus_uni = []
    
    for element in businesses:
        bus = {
        'bus_name': element.get('name'),
        'bus_type': element.get('categories')[0].get('title'), 
        'location': f"{element.get('location').get('address1')}, {element.get('location').get('city')} ({element.get('location').get('zip_code')})",
        'menu_link': None,
        'owner_id':  None,
        'photo_url': element.get('image_url'),
        'price_gauge': element.get('price'),
        'review_count': 0,
        'review_sum': 0,
        }

        bus_uni.append(bus)
    df = pd.DataFrame(data=bus_uni)
    df.index.names = ['bus_id']
    return

if __name__ == '__main__':
    main()



