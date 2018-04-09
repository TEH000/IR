# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class GameItem(scrapy.Item):
    # define the fields for your item here like:
    game_name = scrapy.Field()
    guide_url = scrapy.Field()
    article_head = scrapy.Field()
    edit_time = scrapy.Field()
    article_content = scrapy.Field()
    like = scrapy.Field()


    pass
