package leider.ken.nfl

class SeasonController {
    static scaffold = true
    def yahooStatScraperService
    def armchairAnalysisImportService
    def matchupScraperService
	
    def scrape() {
        yahooStatScraperService.load()
    }
    
    def armchair() {
        armchairAnalysisImportService.load()
    }
    
    def matchups() {
        matchupScraperService.load()
    }
}
