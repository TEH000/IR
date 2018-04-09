from scrapy.spiders import Spider
from scrapy.http import Request
from scrapy.selector import Selector
from GameCrawl.items import GameItem

class GameSpider(Spider):
    name = "gamecrawler"
    allowed_domains = ["ign.com"]


    start_urls = [
        "http://www.ign.com/wikis/Metal-Gear-Solid-5-The-Phantom-Pain",
        "http://www.ign.com/wikis/The-Last-of-Us",
        "http://www.ign.com/wikis/The-Legend-of-Zelda-Oracle-of-Seasons",
        "http://www.ign.com/wikis/Tornado-Mania",
        "http://www.ign.com/wikis/Inside",
        "http://www.ign.com/wikis/pokemon-red-blue-yellow-version",
        "http://www.ign.com/wikis/The-Legend-of-Zelda-Oracle-of-Ages",
        "http://www.ign.com/wikis/The-Legend-of-Zelda-Ocarina-of-Time",
        "http://www.ign.com/wikis/Sonic-The-Hedgehog-Pocket-Adventure",
        "http://www.ign.com/wikis/Grand-Theft-Auto-4",
        "http://www.ign.com/wikis/pokemon-heartgold-soulsilver-version",
        "http://www.ign.com/wikis/The-Legend-of-Zelda-Skyward-Sword",
        "http://www.ign.com/wikis/Infinity-Blade-2",
        "http://www.ign.com/wikis/Super-Mario-Bros",
        "http://www.ign.com/wikis/Red-Dead-Redemption",
        "http://www.ign.com/wikis/metal-gear-solid-4-guns-of-the-patriots",
        "http://www.ign.com/wikis/Grand-Theft-Auto-5",
        "http://www.ign.com/wikis/Undertale",
        "http://www.ign.com/wikis/Super-Mario-Odyssey",
        "http://www.ign.com/wikis/Celeste",
        "http://www.ign.com/wikis/The-Legend-of-Zelda-Breath-of-the-Wild",
        "http://www.ign.com/wikis/Joust",
        "http://www.ign.com/wikis/Super-Mario-Galaxy-2",
        "http://www.ign.com/wikis/Pokemon-Silver-Version",
        "http://www.ign.com/wikis/The-Witness",
        "http://www.ign.com/wikis/Metal-Gear",
    ]

    def parse(self,response):
        selector = Selector(response)
        item = GameItem()
        item['guide_url'] = response.url
        item['game_name'] = response.request.url.split('/')[4]
        item['article_head'] = selector.xpath('/html/body/section[2]/section/article/h1/text()').extract_first()
        item['edit_time'] = selector.xpath('/html/body/section[2]/section/article/div[2]/span/time/span/text()').extract_first()
#

        paragraph = selector.xpath('//*[@id="article-content"]/p//text()').extract()
        article = ''
        for p in paragraph:
            article += p
        article = article.replace("\n", "")
        item['article_content'] = str(article)
        yield item

        base_url = "http://www.ign.com"
        noSub_urls = selector.xpath('/html/body/section[2]/aside/div[1]/div[@class="ghn-L1 ghn-noSub"]/a/@href').extract()
        hasSub_urls = selector.xpath('/html/body/section[2]/aside/div[1]/div[@class="ghn-L1 ghn-hasSub"]/a/@href').extract()

        for url in hasSub_urls:
            yield Request(base_url  + str(url), callback=self.parse_url)

        for url in noSub_urls:
            print(base_url+str(url))
            yield Request(base_url + str(url), callback=self.parse_item)


    def parse_url(self,response):
        selector = Selector(response)
        base_url = "http://www.ign.com"
        l2_urls = selector.xpath('/html/body/section[2]/aside/div[1]/div[@class="ghn-subNav ghn-show"]/div/a/@href').extract()
        l3_urls = selector.xpath('/html/body/section[2]/aside/div[1]/div[@class="ghn-tertNav ghn-show"]/div/a/@href').extract()
        if l2_urls:
            for l2_url in l2_urls:
                yield Request(base_url + str(l2_url), callback=self.parse_item)
        if l3_urls:
            for l3_url in l3_urls:
                yield Request(base_url+ str(l3_url), callback=self.parse_item)

    def parse_item(self, response):
        selector = Selector(response)
        item = GameItem()
        item['guide_url'] = response.url
        item['game_name'] = response.request.url.split('/')[4]
        item['article_head'] = selector.xpath('/html/body/section[2]/section/article/h1/text()').extract_first()
        item['edit_time'] = selector.xpath('/html/body/section[2]/section/article/div[2]/span/time/span/text()').extract_first()

        paragraph = selector.xpath('//*[@id="article-content"]/p//text()').extract()
        article = ''
        for p in paragraph:
            article += p
        article = article.replace("\n", "")
        item['article_content'] = str(article)
        yield item