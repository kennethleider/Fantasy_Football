package leider.ken.nfl.stats

class SeasonController {
    static scaffold = true
    def yahooStatScraperService
    def armchairAnalysisImportService
	
    def scrape() {
        yahooStatScraperService.scrape()
    }
    
    def armchair() {
        armchairAnalysisImportService.load()
    }
}
