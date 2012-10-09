import time
import random

OMGBUSY = """\033[0;32m
>> execute:   Splitting up dataset, running from {0} to {1}
>> routingPattern Connect route "taggable_add_tag" (/admin/addTag/:object_class/:object_id)
>> routingPattern Connect route "taggable_remove_tag" (/admin/removeTag/:object_class/:object_id)
>> routingPattern Connect route "prestaSitemap_index" (/sitemap.xml)
>> routingPattern Connect route "prestaSitemap_map" (/sitemap.:mapName.xml)
>> routingPattern Connect route "captcha" (/captcha)
>> routingPattern Connect route "captcha_refresh" (/captcha/:random)
>> routingPattern Connect route "captcha_demo" (/captcha_demo)
>> routingPattern Connect route "appl_signin" (/login)
>> routingPattern Connect route "appl_signout" (/logout)
>> routingPattern Connect route "appl_password" (/request_password)
>> routingPattern Match route "homepage" (/) for / with parameters array (  'module' => 'page',  'action' => 'show',  'internal_slug' => 'homepage',)
\033[0m"""

i = 0
split = 25

while True:
    rando = float(random.randint(0,100)/100.0)
    now = time.strftime("%Y %d %b %H:%M:S +0000");

    if i > 0 and i % split == 0:
        print "\nfinished indexing at {0}".format(now)
        print OMGBUSY.format(i, i + (split - 1))
        time.sleep( rando )
        print "\nstarting indexing at {0}".format(now)

    if i == 0:
        print "\nstarting indexing at {0}".format(now)
        print OMGBUSY.format(i, i + (split - 1))
        time.sleep( rando )

    time.sleep( rando )
    print ">> execute: seeding index {0}".format(i)
    i += 1

