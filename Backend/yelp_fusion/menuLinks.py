"""
@author Sam DeFrancisco

Trying to scrape /menu pages from yelp

"""

from urllib.parse import urlparse
import requests
from bs4 import BeautifulSoup
import json




def google(rest: str) -> str:
    response = requests.get(f'https://www.google.com/search?q={rest}')
    soup = BeautifulSoup(response.text, "html.parser")
    links = soup.find_all('a')
    results = []
    for a in links:
        if '/url?q' in a['href']:
            linkHref = a['href']
            linkHref = linkHref.split('q=')[1]
            # gives www.{site}.com without all extra bs
            s = urlparse(linkHref).netloc
            results.append(s)
    mostFreq = max(set(results), key=results.count)
    print(mostFreq + '\n')
    return mostFreq


google('Hickory Park')
google('Potbelly')
google('Freddys')
google('blaze pizza')
google('Cafe Beaudelaire')
google('Ichiban Japanese Restaurant')
google('macubana')
google('Stomping Grounds Cafe')
# # Gets yelp landing page for resturant
# # found out this is pointless bc business search returns the yelp /biz page
# def yelp_biz_str(rest: str):
#     response = requests.get(f"https://www.yelp.com/search?find_desc={rest}&find_loc=Ames%2C+IA")
#     soup = BeautifulSoup(response.text, 'html.parser')
#     lis = soup.find_all('li', recursive=True)
#     for li in lis:
#         links = li.find_all('a', href=True)
#         for link in links:
#             if '/biz' in link['href']:
#                 return link['href']
#     return ""


# rests = {'provisions', 'potbelly', 'pad thai', 'taste place'}
# for rest in rests:
#     print(yelp_biz_str(rest))
# s='https://www.yelp.com/biz/potbelly-sandwich-shop-ames?osq=potbelly'
# rest = 'https://www.yelp.com/menu/potbelly-sandwich-shop-ames'
# rstr = 'https://www.yelp.com/menu' + rest
# response = requests.get('https://www.yelp.com/menu/provisions-lot-f-ames-2')

# print(response.url)
# soup = BeautifulSoup(response.text, 'html.parser')
#print(soup.prettify())


# # found /menu
# print(try_yelp_menu('https://www.yelp.com/biz/mr-burrito-ames'))
# # didnt find menu
# print(try_yelp_menu('https://www.yelp.com/biz/provisions-lot-f-ames-2'))

# print(try_yelp_menu('https://www.yelp.com/biz/taste-place-ames'))