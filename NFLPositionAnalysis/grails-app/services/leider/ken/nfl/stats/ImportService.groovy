package leider.ken.nfl.stats

class ImportService {
    
    public def process(String name, long total, Closure groupIterator, Closure worker) throws Exception {
        int i = 0
        def retval = []
        
        while(i < total) {
            def group = groupIterator()
            long start = System.currentTimeMillis()
            for (def value in group) {
                retval.add(worker(value))
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
