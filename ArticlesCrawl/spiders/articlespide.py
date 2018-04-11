from scrapy.spiders import Spider
from scrapy.http import Request
from scrapy.selector import Selector
from ArticlesCrawl.items import ArticlescrawlItem

class ArticleSpide(Spider):
    name = "articlecrawler"
    allowed_domains = ["ign.com"]
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36'
    }



    start_urls = [
        "http://www.ign.com/articles/games/the-legend-of-zelda-oracle-of-seasons/gbc-16042",
        "http://www.ign.com/articles/games/the-legend-of-zelda-oracle-of-ages/gbc-16041",
        "http://www.ign.com/articles/games/metal-gear-solid-5/xbox-one-20000541",
        "http://www.ign.com/articles/games/pokemon-red-version/gb-9846",
        "http://www.ign.com/articles/games/robopon-2-cross-version/gba-16933",
        "http://www.ign.com/articles/games/tornado-mania/cell-866280",
        "http://www.ign.com/articles/games/asteroids-hyper-64/n64-427",
        "http://www.ign.com/articles/games/pac-man-championship-edition-dx/xbox-360-77723",
        "http://www.ign.com/articles/games/the-legend-of-zelda-skyward-sword/wii-872155",
        "http://www.ign.com/articles/games/grand-theft-auto-iv/xbox-360-827005",
        "http://www.ign.com/articles/games/the-legend-of-zelda-ocarina-of-time-and-master-quest/gcn-495087",
        "http://www.ign.com/articles/games/pokemon-heartgold-version/nds-14348187",
        "http://www.ign.com/articles/games/metal-gear-solid-3-subsistence/ps2-748590",
        "http://www.ign.com/articles/games/uncharted-3-drakes-deception/ps3-94314",
        "http://www.ign.com/articles/games/snk-vs-capcom-match-of-the-millennium/ngpc-12975",
        "http://www.ign.com/articles/games/sonic-the-hedgehog-pocket-adventure/ngpc-12670",
        "http://www.ign.com/articles/games/grand-theft-auto-iv/xbox-360-827005",
        "http://www.ign.com/articles/games/red-dead-redemption-undead-nightmare/ps3-80424",
        "http://www.ign.com/articles/games/pokemon-blue-version/gb-16708",
        "http://www.ign.com/articles/games/infinity-blade-ii/iphone-119380",
        "http://www.ign.com/articles/games/the-last-of-us-left-behind/ps3-20008511",
        "http://www.ign.com/articles/games/super-mario-bros-deluxe/gbc-11703",
        "http://www.ign.com/articles/games/first-person-tetris/web-57801",
        "http://www.ign.com/articles/games/dragon-ball-z-budokai-3/ps2-679951",
        "http://www.ign.com/articles/games/grand-theft-auto-san-andreas/ps2-611957",
        "http://www.ign.com/articles/games/metal-gear-solid-4-guns-of-the-patriots/ps3-714044",
        "http://www.ign.com/articles/games/pokemon-yellow-special-pikachu-edition/gb-12045",
        "http://www.ign.com/articles/games/undertale/pc-20003810",
        "http://www.ign.com/articles/games/chrono-trigger/snes-6858",
        "http://www.ign.com/articles/games/celeste/nintendo-switch-20065379",
        "http://www.ign.com/articles/games/super-mario-odyssey/nintendo-switch-20009249",
        "http://www.ign.com/articles/games/the-legend-of-zelda-links-awakening/gb-5950",
        "http://www.ign.com/articles/games/excitebike/nes-7159",
        "http://www.ign.com/articles/games/the-legend-of-zelda-breath-of-the-wild/wii-u-110801",
        "http://www.ign.com/articles/games/mario-golf-gb/gbc-12206",
        "http://www.ign.com/articles/games/soulcalibur-iii/ps2-736896",
        "http://www.ign.com/articles/games/pokemon-soulsilver-version/nds-14348189",
        "http://www.ign.com/articles/games/shanghai/nds-774655",
        "http://www.ign.com/articles/games/midway-arcade-treasures/xbox-569667",
        "http://www.ign.com/articles/games/tom-clancys-splinter-cell/xbox-481175",
        "http://www.ign.com/articles/games/super-mario-galaxy-2/wii-14354736",
        "http://www.ign.com/articles/games/metal-gear-solid-4-guns-of-the-patriots/ps3-714044",
    ]

    def parse(self,response):
        # selector = Selector(response)
        # article_page = selector.xpath('//*[@id="search-list"]/div[1]/div/div[5]/div[2]/a/@href').extract_first().strip()
        # yield Request(article_page, callback=self.parse_url)
        selector = Selector(response)
        article_url = selector.xpath('/html/body/div[2]/div[4]/div[1]/div/div[2]/ul/li/h3/a/@href').extract()

        game_name = selector.xpath('/html/body/div[2]/div[3]/div/h2/a/text()').extract_first().strip()
        if article_url:
            for url in article_url:
                yield Request(url, meta={'game_name': game_name}, callback=self.parse_item)
        next_page = selector.xpath('/html/body/div[2]/div[4]/div[1]/a/@href').extract_first()
        if next_page:
            yield Request('http://www.ign.com/' + next_page, callback=self.parse)

    # def parse_url(self,response):
    #     selector = Selector(response)
    #     article_url = selector.xpath('/html/body/div[2]/div[4]/div[1]/div/div[2]/ul/li/h3/a/@href').extract()
    #
    #     game_name = selector.xpath('/html/body/div[2]/div[3]/div/h2/a/text()').extract_first().strip()
    #     if article_url:
    #         for url in article_url:
    #             yield Request(url,meta={'game_name': game_name},callback=self.parse_item)
    #     next_page = selector.xpath('/html/body/div[2]/div[4]/div[1]/a/@href').extract_first()
    #     if next_page:
    #         yield Request('http://www.ign.com/'+next_page,callback=self.parse_url)



    def parse_item(self, response):
        selector = Selector(response)
        item = ArticlescrawlItem()
        item['article_url'] = response.url
        item['game_name'] = response.meta['game_name']
        title = ''
        item['article_title'] = title.join(selector.xpath('/html/body/section[2]/section/article/h1//text()').extract())
        item['article_date'] = selector.xpath('/html/body/section[2]/section/article/div[1]/span/meta/@content').extract_first()
        item['subtitle'] = selector.xpath('//*[@id="article-content"]/div[1]/text()').extract()[1].strip()

        paragraph = selector.xpath('//*[@id="article-content"]/p//text()').extract()
        article = ''
        for p in paragraph:
            article += p
        article = article.replace("\n", "")
        item['article_content'] = str(article)
        yield item