import spring.battle.groovy.ImportController
import spring.battle.groovy.JsonParser
import spring.battle.groovy.XmlParser

beans {

    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])
    mvc.'annotation-driven'()

    jsonParser JsonParser
    xmlParser XmlParser

    importController ImportController, xmlParser

//    service(ServiceImpl) {
//        repository = ref('repository')
//    }
//
//
//    repository(RepositoryImpl){
//        text = '${test.text}'
//    }
//
//    //namespace example
//    xmlns task: 'http://www.springframework.org/schema/task'
//    task.'scheduled-tasks'(scheduler: 'myScheduler') {
//        task.scheduled(ref: 'service', method: 'printPeriodically', 'fixed-delay': 500)
//    }
//
//    task.scheduler(id:'myScheduler', 'pool-size':10)
//
//    //props configurer example
//    props(PropertyPlaceholderConfigurer){
//        location = 'test.properties'
//    }
}