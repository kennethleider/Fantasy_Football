// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = "leider.ken.nfl.stats" // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      pdf: 'application/pdf',
                      rtf: 'application/rtf',
                      excel: 'application/vnd.ms-excel',
                      ods: 'application/vnd.oasis.opendocument.spreadsheet',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
    
    //trace 'org.hibernate.SQL'
}

regularSeasonWeeks = 17
armchairanalysis.dir = "E:\\temp\\armchairanalysis"
playerPositionOrder = [ 'QB', 'RB', 'WR', 'TE' ]
player.reconciliation = [
        "nfl" : [
                "yahoo" : [
                        'WIL446858' : '24076',
                        'BRO000001' : '24171',
                        'MIL625333' : '9444',
                        'PET260705' : '8261',
                        'SMI725035' : '8305',
                        'DAV233437' : '8382',
                        'DAV233157' : '8521',
                        'MIL625718' : '8292',
                        'WIL179825' : '7181',
                        'WIL447983' : '7186',
                        'PET263012' : '6134',
                        'WIL493535' : '6178',
                        'SMI733120' : '5521',
                        'WIL271115' : '4653',
                ]
        ],
        "yahoo" : [
                "armchairanalysis" : [
                        '8261' : 'AP-070',
                        '4653' : 'RW-260',
                        '7181' : 'CW-220',
                        '8524' : 'DW-100',
                        '5521' : 'SS-210',
                        '24171' : 'AB-350',
                        '9001' : 'SJ-060',
                        '24076' : 'MW-280',
                        '7448' : 'JC-560',
                        '7186' : 'MW-270',
                        '8263' : 'TG-090',
                        '8333' : 'MW-040',
                        '8305' : 'SS-220',
                        '6791' : 'BW-070',
                        '4693' : 'JK-160',
                        '25284' : 'MH-260',
                        '2732' : 'BB-070',
                        '3504' : 'TB-120',
                        '4084' : 'MH-170',
                        '4136' : 'DS-180',
                        '4268' : 'CC-230',
                        '4704' : 'MC-160',
                        '4785' : 'NL-060',
                        '5239' : 'BS-340',
                        '5304' : 'DJ-180',
                        '5506' : 'MT-210',
                        '5584' : 'VS-030',
                        '5758' : 'BG-030',
                        '5759' : 'PC-100',
                        '5841' : 'PB-020',
                        '5973' : 'CR-180',
                        '6134' : 'AP-080',
                        '6178' : 'RW-270',
                        '6298' : 'BL-020',
                        '6437' : 'DD-040',
                        '6499' : 'BS-350',
                        '6605' : 'AB-355',
                        '6621' : 'CF-060',
                        '6647' : 'ZH-010',
                        '6967' : 'AE-010',
                        '7023' : 'RL-040',
                        '7091' : 'RB-180',
                        '7451' : 'TD-120',
                        '7623' : 'BC-090',
                        '7636' : 'MJ-110',
                        '8136' : 'WB-130',
                        '8508' : 'JH-470',
                        '8896' : 'KM-260',
                        '8548' : 'MB-060',
                        '9122' : 'SG-050',
                        '8627' : 'MW-310'
                ]
        ]
]


yahooTeamCodes = [
    'TAM' : 'TB',
    'KAN' : 'KC',
    'SDG' : 'SD',
    'SFO' : 'SF',
    'NOR' : 'NO',
    'NWE' : 'NE',
    'GNB' : 'GB'
]

url.cache.app.dir = "${userHome}/.grails/${appName}/url_cache"
