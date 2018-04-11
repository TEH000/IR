# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class ArticlescrawlItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    game_name = scrapy.Field()
    article_url = scrapy.Field()
    article_title = scrapy.Field()
    subtitle = scrapy.Field()
    article_date = scrapy.Field()
    article_content = scrapy.Field()

    pass
