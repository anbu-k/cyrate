"""
@author Sam DeFrancisco

Trying to scrape /menu pages from yelp

"""

from bs4 import BeautifulSoup
import requests
import json

# Not really doing anything
def google(rest: str):
    response = requests.get(f'https://www.google.com/search?q={rest}')
    soup = BeautifulSoup(response.text, "html.parser")
    divs = soup.find_all('div')
    for div in divs:
        if 'Menu' in div.text:
            print(f'{div.find("a")}\n')


def try_yelp_menu(url: str):
    # 'https://www.yelp.com/biz/four-barrel-coffee-san-francisco'
    # after split
    # ['https://www.yelp.com/', 'four-barrel-coffee-san-francisco']
    rs = url.split('/biz')
    name = rs[1]
    search = 'https://www.yelp.com/menu' + name
    response = requests.get(search)
    if response.url != search:
        return f"No /menu page for {name}"
    return search
    

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


# found /menu
print(try_yelp_menu('https://www.yelp.com/biz/mr-burrito-ames'))
# didnt find menu
print(try_yelp_menu('https://www.yelp.com/biz/provisions-lot-f-ames-2'))

print(try_yelp_menu('https://www.yelp.com/biz/taste-place-ames'))