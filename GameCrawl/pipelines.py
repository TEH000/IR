# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
import os

class GamecrawlPipeline(object):
    def process_item(self, item, spider):
        base_dir = os.getcwd()
        foldername = base_dir+'/'+item['game_name']
        if (not os.path.exists(foldername)):
            os.makedirs(foldername)
        fiename = base_dir+'/'+item['game_name']+'/'+item['article_head']+'.txt'
        if os.path.exists(fiename):
            pass
        else:
            with open(fiename, 'a') as f:
                f.write(item['article_head'] + '\n')
                f.write(item['guide_url'] + '\n')
                f.write(item['edit_time'] + '\n')
                f.write(item['article_content'] + '\n\n')
                return item
