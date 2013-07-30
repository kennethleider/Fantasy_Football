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
yahooLookup = [
    8261L : 'AP-070',
    4653L : 'RW-260',
    7181L : 'CW-220',
    8524L : 'DW-100',
    5521L : 'SS-210',
    24171L : 'AB-350',
    9001L : 'SJ-060',
    24076L : 'MW-280',
    7448L : 'JC-560',
    7186L : 'MW-270',
    8263L : 'TG-090',
    8333L : 'MW-040',
    8305L : 'SS-220',
    6791L : 'BW-070',
    4693L : 'JK-160',
    25284L : 'MH-260',
    2732L : 'BB-070',
    3504L : 'TB-120',
    4084L : 'MH-170',
    4136L : 'DS-180',
    4268L : 'CC-230',
    4704L : 'MC-160',
    4785L : 'NL-060',
    5239L : 'BS-340',
    5304L : 'DJ-180',
    5506L : 'MT-210',
    5584L : 'VS-030',
    5758L : 'BG-030',
    5759L : 'PC-100',
    5841L : 'PB-020',
    5973L : 'CR-180',
    6134L : 'AP-080',
    6178L : 'RW-270',
    6298L : 'BL-020',
    6437L : 'DD-040',
    6499L : 'BS-350',
    6605L : 'AB-355',
    6621L : 'CF-060',
    6647L : 'ZH-010',
    6967L : 'AE-010',
    7023L : 'RL-040',
    7091L : 'RB-180',
    7451L : 'TD-120',
    7623L : 'BC-090',
    7636L : 'MJ-110',
    8136L : 'WB-130',
    8508L : 'JH-470',
    8896L : 'KM-260',
    8548L : 'MB-060',
    9122L : 'SG-050',
    8627L : 'MW-310'
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
