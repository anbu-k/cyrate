# -*- coding: utf-8 -*-
"""
Created on Wed Sep 28 11:31:56 2022

@author: sjdef
"""

import requests
import json
import pandas as pd


headers = {"Authorization": "Bearer w_IaAch4CRD-Mb70s8AHJz4X_PQGUuGwZIvi9vXhGyL7_jTut-4Bgr5A61QiS8vtnryhEvCAyQUNhIRvB29yV2l_xAElHlq_Y946nRxJMlhPCC5JRBlbSr3w2xUyY3Yx"}
url = 'https://api.yelp.com/v3/businesses/beWPrqxjMwGiOEiJBOY85A'

data = requests.get(url, headers=headers)

data = data.json()

hours=data.get('hours')[0].get('open')
days_dic = {0:"Mon", 1:"Tue", 2:"Wed",3:"Thu",4:"Fri",5:"Sat", 6:"Sun"}

ret = ""
for element in hours:
    #(element)
    ret += f"{days_dic.get(element.get('day'))}: {element.get('start')} - {element.get('end')} "