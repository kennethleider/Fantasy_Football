package leider.ken.nfl.stats

class ImportService {
    def sessionFactory
        
     public def process(String name, Collection group, Closure worker) throws Exception {
         process(name, group.size(), { return group }, worker)
     }
    
    public def process(String name, long total, Closure groupIterator, Closure worker) throws Exception {
        int i = 0
        def retval = []
        
        while(i < total) {
            def group = groupIterator()
            long start = System.currentTimeMillis()
            group.eachWithIndex { value, index ->
                retval.add(worker(value, index))
            }

            i += group.size()
            sessionFactory.currentSession.flush()
            sessionFactory.currentSession.clear()
            org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP.get().clear()
            
            println "=== ${name}: ${i} of ${total}: ${(System.currentTimeMillis() - start)/group.size()}"
        }
        
        return retval
    }
}
