package leider.ken.nfl.stats

class ImportService {
    def sessionFactory
        
    public def process(String name, Collection group, Closure worker) throws Exception {
        process(name, group.size(), { return group }, worker)
    }
    
    public def process(String name, Closure gather, Closure worker, int groupSize = 100) {
        int i = 0
        def retval = []
        def startImport = System.currentTimeMillis()
        
        def gathered = gather()
        long total = gathered.size()
        println "= ${name}: gathered ${total} in ${(System.currentTimeMillis() - startImport)/1000}"
         
        def groups = gathered.asList().collate(groupSize)
        
        for (def group in groups) {
            long start = System.currentTimeMillis()
            group.eachWithIndex { value, index ->
                retval.add(worker(value, index))
            }
            
            i += group.size()
            sessionFactory.currentSession.flush()
            org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP.get().clear()
            
            println "   Processed ${i} of ${total}.  AVG - ${(System.currentTimeMillis() - start)/(1000 * group.size())}"
        }
        
        if (total > 0) {
           println "= ${name}: import completed in ${(System.currentTimeMillis() - startImport)/1000}"
        }
        return retval
    }
    
    public def process(String name, long total, Closure groupIterator, Closure worker) throws Exception {
        int i = 0
        def retval = []
        def begin = System.currentTimeMillis()
        
        while(i < total) {
            def group = groupIterator()
            long start = System.currentTimeMillis()
            group.eachWithIndex { value, index ->
                retval.add(worker(value, index))
            }
            
            i += group.size()
            sessionFactory.currentSession.flush()
            //sessionFactory.currentSession.clear()
            org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP.get().clear()
            
            println "=== ${name}: ${i} of ${total}: ${(System.currentTimeMillis() - start)/group.size()}"
            
        }
        
        println "=== ${name} complete: processed ${total} records in ${(System.currentTimeMillis() - begin)} milliseconds"
        
        return retval
    }
}
