package com.skynet.initdata.relational;

import com.skynet.initdata.entity.RelationalChangeSetEntity;
import com.skynet.initdata.repository.RelationalChangeSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class RelationChangeSetProcessor {

    private final RelationalChangeSetRepository changeSetRepository;
    private final CarrierChangeLogsRelational carrierChangeLogsRelational;

    public void process() {
        List<RelationalChangeSetEntity> changeSetEntityDbList = changeSetRepository.findAll();
        Set<String> changeSetIds = new HashSet<>();
        changeSetEntityDbList.forEach(relationalChangeSetEntity -> changeSetIds.add(relationalChangeSetEntity.getChangeSetId()));

        List<RelationalChangeSetEntity> newlyAddedChangeSetEntity = new ArrayList<>();

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
        for (BeanDefinition bd : scanner.findCandidateComponents("com.skynet.initdata.relational")) {
            try {
                Class<?> cls = Class.forName(bd.getBeanClassName());
                for (Method method : cls.getDeclaredMethods()) {
                    RelationalChangeSet relationalChangeSet = method.getAnnotation(RelationalChangeSet.class);
                    if (relationalChangeSet != null) {
                        String id = relationalChangeSet.id();
                        if (!changeSetIds.contains(id)) {
                            method.invoke(carrierChangeLogsRelational);
                            RelationalChangeSetEntity relationalChangeSetEntity = new RelationalChangeSetEntity();
                            relationalChangeSetEntity.setChangeSetId(id);
                            relationalChangeSetEntity.setAuthorName(relationalChangeSet.author());
                            relationalChangeSetEntity.setMethodName(method.getName());
                            newlyAddedChangeSetEntity.add(relationalChangeSetEntity);
                        }
                    }
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        if (!newlyAddedChangeSetEntity.isEmpty()) {
            List<RelationalChangeSetEntity> changeSetEntityList = changeSetRepository.saveAll(newlyAddedChangeSetEntity);
            System.out.println("hhh");
        }
    }

}
