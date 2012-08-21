package leider.ken.nfl.armchairanalysis

import com.csvreader.CsvReader;

import leider.ken.nfl.Season
import leider.ken.nfl.Week
import leider.ken.nfl.Player
import leider.ken.nfl.stats.PlayerStats
import leider.ken.nfl.stats.PlayerWeekStats

class ArmchairAnalysisImportService {
    static transactional = false
    def sessionFactory
    def propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP    
    def grailsApplication
    
    def load() {
        process(processGames, grailsApplication.config.armchairanalysis.dir + "\\GAMES.csv")
        process(processPlayers, grailsApplication.config.armchairanalysis.dir + "\\ROSTER.csv")
        process(processPlays, grailsApplication.config.armchairanalysis.dir + "\\FLAT_FILE.csv")
        process(processOffense, grailsApplication.config.armchairanalysis.dir + "\\OFFENSE.csv")
        computeWeekStats()
    }
    
    private void process(Closure closure, String fileName) throws Exception {
        CsvReader reader = new CsvReader(fileName);
        reader.readHeaders();
        String[] headers = reader.getHeaders();
        
        boolean done = false
        
        while (!done) {
            long start = System.currentTimeMillis()
            long records = 100
            for (int i in 1..records) {
                if (reader.readRecord()) {
                    closure(reader)
                } else {
                    done = true
                    break;
                }
            }
            sessionFactory.currentSession.flush()
            sessionFactory.currentSession.clear()
            propertyInstanceMap.get().clear()
            println "=== ${fileName}: ${reader.getCurrentRecord() + 1}: ${(System.currentTimeMillis() - start)/records} " 
        }
    }
    
    private processGames = { reader ->
        Game game = Game.get(reader.get("GID"))
   
        if (game == null) {
            game = new Game()
            game.id = reader.get("GID").toLong()
            
            Integer year = reader.get("SEAS").trim().toInteger()
            Integer week = reader.get("WEEK").trim().toInteger()
            
            game.week = Week.findOrSaveWhere(season : Season.findOrSaveWhere(year : year),
                number : week)
            //println "Week Lookup ${System.currentTimeMillis() - start}";                
            game.day = reader.get("DAY")
            game.visitor = reader.get("V")
            game.host = reader.get("H")
            game.stadium = reader.get("STAD")
            game.temperature = reader.get("TEMP")
            game.humidity = reader.get("HUMD")
            game.windSpeed = reader.get("WSPD")
            game.windDirection = reader.get("WDIR")
            game.weatherConditions = reader.get("COND")
            game.surface = reader.get("SURF")
            game.overUnder = reader.get("OU").trim().toDouble()
            game.visitorPointSpread = reader.get("SPRV").trim().toDouble()
            game.visitorScore = reader.get("PTSV").trim().toInteger()
            game.hostScore = reader.get("PTSH").trim().toInteger()
            
            game.save()
        }
    }
    
     private processPlays = { reader ->
        Play play = Play.get(reader.get("PID").toLong())
   
        // DETAIL DSEQ QTR MIN SEC PTSO	PTSD	TIMO	TIMD	DWN	YTG	YFOG ZONE
        // SG	NH  KNE SPK
        // RTCK1	RTCK2  DFB PTCK1	PTCK2
        // PTM1	PEN1	DESC1 CAT1	PEY1	PDO1	PTM2	PEN2	DESC2	CAT2	PEY2	PDO2 PTM3	PEN3	DESC3	CAT3	PEY3	PDO3

        // LT	LG	C	RG	RT
        if (play == null) {
            play = new Play()
            play.id = reader.get("PID").toLong()
            play.game = Game.get(reader.get("GID").toLong())            																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																														
            play.offense = reader.get("OFF")
            play.defense = reader.get("DEF")
            play.type = reader.get("TYPE")
            play.duration = toInt(reader.get("LEN"))
            play.yards = toInt(reader.get("YDS"))
            play.successful = toBoolean(reader.get("SUCC"))
            play.firstDown = toBoolean(reader.get("1ST"))
            play.points = toInt(reader.get("PTS"))
            play.td = play.points > 6 ? 1 : 0
            play.conversion = play.type == "CONV" && play.successful == 1 ? 1 : 0
            
            if (!play.save()) {
                println play.errors
            }

            if (reader.get("BC").trim()) {
                Rush rush = new Rush(play : play)
                rush.player = getPlayer(reader.get("BC"))
                rush.direction = reader.get("DIR")
                if (!rush.save()) {
                    println rush.errors
                }
            }
            
            if (reader.get("PSR").trim()) {
                Pass pass = new Pass(play : play)
                pass.passer = getPlayer(reader.get("PSR"))
                pass.target = getPlayer(reader.get("TRG")) 
                pass.sack = reader.get("SK1").trim().isEmpty() ? 0 : 1
                pass.location = reader.get("LOC")
                pass.completion = toBoolean(reader.get("COMP"))
                pass.interception = reader.get("INT").trim().isEmpty() ? 0 : 1
                if (!pass.save()) {
                    println pass.errors
                }
            }
            
            if (reader.get("PUNTER").trim()) {
                Punt punt = new Punt(play : play)
                punt.punter = getPlayer(reader.get("PUNTER"))
                punt.returner = getPlayer(reader.get("PR"))
                punt.grossYards = toInt(reader.get("PGRO"))
                punt.netYards = toInt(reader.get("PNET"))
                punt.returnYards = toInt(reader.get("PRY"))
                punt.touchBack = toBoolean(reader.get("PTB"))
                punt.fairCatch = toBoolean(reader.get("PFC"))
                if (!punt.save()) {
                    println punt.errors
                }
            }
            
            if (reader.get("KICKER").trim()) {
                Kick kick = new Kick(play : play)
                kick.kicker = getPlayer(reader.get("KICKER"))
                kick.returner = getPlayer(reader.get("KR"))
                kick.grossYards = toInt(reader.get("KGRO"))
                kick.netYards = toInt(reader.get("KNET"))
                kick.returnYards = toInt(reader.get("KRY"))
                kick.touchBack = toBoolean(reader.get("KTB"))
                if (!kick.save()) {
                    println kick.errors
                }                
            }

            // BLK	FGXP	FKICKER	DIST	GOOD
            //FUM	RECV  SAF
            // INT	IRY
            // SK1	SK2
        }
    }
    
    private processPlayers = { reader ->
        Player player = getPlayer(reader.get("PLAYER"))
        
        if (player == null) {
            ArmchairPlayerRef ref = new ArmchairPlayerRef()
            ref.id = getPlayerId(reader.get("PLAYER"))
            ref.code = reader.get("PLAYER")
            ref.player = new Player()
            ref.player.name = reader.get("FNAME") + " " + reader.get("LNAME")
            ref.player.position = reader.get("POS1")
            ref.player.alternatePosition = reader.get("POS2")
                        
            ref.player.save()
            ref.save()
        }
    }
    
    private processOffense = { reader ->
        Player player = getPlayer(reader.get("PLAYER"))
        Game game = Game.get(reader.get("GID"))
        
        PlayerWeekStats stats = PlayerWeekStats.findByWeekAndPlayer(game.week, player)
   
        if (stats == null) {
            stats = new PlayerWeekStats(player : player, week : game.week)
            stats.passing.attempts = toInt(reader.get("PA"))
            stats.passing.completions = toInt(reader.get("PC"))
            stats.passing.yards = toInt(reader.get("PY"))
            stats.passing.TDs = toInt(reader.get("TDP"))
            stats.passing.interceptions = toInt(reader.get("INT"))
            
            stats.rushing.attempts = toInt(reader.get("RA"))
            stats.rushing.yards = toInt(reader.get("RY"))
            stats.rushing.TDs = toInt(reader.get("TDR"))
            stats.rushing.fumblesLost = toInt(reader.get("FUML"))
            
            stats.receiving.receptions = toInt(reader.get("REC"))
            stats.receiving.yards = toInt(reader.get("RECY"))
            stats.receiving.TDs = toInt(reader.get("TDRE"))
            stats.save()
        }
    }
    
    
    private void computeWeekStats() {        
        processResults("sacks", 
            Play.executeQuery("\
            select game.week, pass.passer, count(*), -1 * sum(play.yards)\
            from Pass as pass, Play as play, Game as game\
            where pass.play = play\
            and play.game = game\
            and pass.sack = 1\
            group by game.week, pass.passer\
            "),
            { stats, row -> 
                stats.passing.sacks = row[2]
                stats.passing.sackYards = row[3]
            }
        )
       
        processResults("targets", 
            Play.executeQuery("\
            select game.week, pass.target, count(*)\
            from Pass as pass, Play as play, Game as game\
            where pass.play = play\
            and play.game = game\
            and pass.target is not null\
            group by game.week, pass.target\
            "), 
            { stats, row -> 
                stats.receiving.targets = row[2]
            }
        )

        processResults("passing conversions", 
            Play.executeQuery("\
            select game.week, pass.passer, sum(play.conversion)\
            from Pass as pass, Play as play, Game as game\
            where pass.play = play\
            and play.game = game\
            and play.conversion = 1\
            group by game.week, pass.passer\
            "), 
            { stats, row -> 
                stats.passing.conversions = row[2]
            }
        )
        
        processResults("receiving conversions", 
            Play.executeQuery("\
            select game.week, pass.target, sum(play.conversion)\
            from Pass as pass, Play as play, Game as game\
            where pass.play = play\
            and play.game = game\
            and play.conversion = 1\
            group by game.week, pass.target\
            "), 
            { stats, row -> 
                stats.receiving.conversions = row[2]
            }
        )
        
        processResults("rushing conversions", 
            Play.executeQuery("\
            select game.week, rush.player, sum(play.conversion)\
            from Rush as rush, Play as play, Game as game\
            where rush.play = play\
            and play.game = game\
            and play.conversion = 1\
            group by game.week, rush.player\
            "), 
            { stats, row -> 
                stats.rushing.conversions = row[2]
            }
        )
    }
    
    private void processResults(String name, def results, Closure closure) {
        boolean done = false
        
        int i = 1
        int records = 1000
        long start = System.currentTimeMillis()
        for (def row in results) {

             PlayerStats stats = PlayerWeekStats.findByWeekAndPlayer(row[0], row[1])
             if (stats == null) {
                 stats = new PlayerWeekStats(week : row[0], player : row[1])
             }
             closure(stats, row)
             stats.save()
             
            if (i++ % records == 0 && i != 0 || i == results.size()) {
                sessionFactory.currentSession.flush()
                sessionFactory.currentSession.clear()
                propertyInstanceMap.get().clear()
                println "=== ${name}: ${i} of ${results.size()}: ${(System.currentTimeMillis() - start)/records} "
                start = System.currentTimeMillis()
            }
        } 
    }
    
    private Player getPlayer(String code) {
        if (code) {
            return ArmchairPlayerRef.get(getPlayerId(code))?.player
        } else {
            return null;
        }
    }
    
    private int getPlayerId(String code) {  
        def a= 'A' as char
        int id = (code.charAt(0) - a) * 26 + (code.charAt(1) - a)
        id *= 1000
        id += code.substring(3).toInteger()
        return id
    }
    
    private int toInt(String field) {
        field = field.trim()
        if (field) {
            return field.toInteger()
        }
        
        return 0
    }
    
    private int toBoolean(String field) {
        field = field.trim()
        if (field) {
            return field.equals("Y") ? 1 : 0
        }
        
        return 0
    }
}
