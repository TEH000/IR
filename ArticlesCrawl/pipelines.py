# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
import os

class ArticlescrawlPipeline(object):
    def process_item(self, item, spider):
        base_dir = os.getcwd()
        filename = base_dir+'/articles.txt'
        # if item['article_content']:
        with open(filename, 'a') as f:
            f.write('-----' + '\n')
            f.write('<url>' + item['article_url'] + '\n')
            f.write('<name>' + item['game_name'] + '\n')
            f.write('<title>' + item['article_title'] + '\n')
            f.write('<subtitle>' + item['subtitle'] + '\n')
            f.write('<time>' + item['article_date'] + '\n')
            f.write('<content>' + item['article_content'] + '\n\n')
            return item
        # foldername = base_dir+'/'+item['game_name']
        # if (not os.path.exists(foldername)):
        #     os.makedirs(foldername)
        # fiename = base_dir+'/'+item['game_name']+'/'+item['article_head']+'.txt'
        # if os.path.exists(filename):
        #     pass
        # else:
        #     with open(filename, 'a') as f:
        #         f.write(item['article_head'] + '\n')
        #         f.write(item['guide_url'] + '\n')
        #         f.write(item['edit_time'] + '\n')
        #         f.write(item['article_content'] + '\n\n')
        #         return item

