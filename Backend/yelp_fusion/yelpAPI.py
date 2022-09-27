import requests
import json

def main():
    headers = {"Authorization": "Bearer w_IaAch4CRD-Mb70s8AHJz4X_PQGUuGwZIvi9vXhGyL7_jTut-4Bgr5A61QiS8vtnryhEvCAyQUNhIRvB29yV2l_xAElHlq_Y946nRxJMlhPCC5JRBlbSr3w2xUyY3Yx"}
    url = 'https://api.yelp.com/v3/businesses/search?location=Ames'
    data = requests.get(url, headers=headers)
    data = data.json()
    print(data)
    print("hello")
    return

if __name__ == '__main__':
    main()