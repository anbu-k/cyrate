import requests
import json

# returns all results from business search location = "ames" from yelp_api
def main():
    headers = {"Authorization": "Bearer w_IaAch4CRD-Mb70s8AHJz4X_PQGUuGwZIvi9vXhGyL7_jTut-4Bgr5A61QiS8vtnryhEvCAyQUNhIRvB29yV2l_xAElHlq_Y946nRxJMlhPCC5JRBlbSr3w2xUyY3Yx"}
    url = 'https://api.yelp.com/v3/businesses/search?location=Ames&radius=40000&limit=50'
    data = requests.get(url, headers=headers)
    data = data.json()
    businesses = data.get('businesses')

    for element in businesses:
        name = element.get('name')
        print(f'{name}')

    
    return

if __name__ == '__main__':
    main()