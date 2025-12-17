package com.BuildMaster.Project_Tracker.Service.Implementations;

import com.BuildMaster.Project_Tracker.Entity.AuditLog;
import com.BuildMaster.Project_Tracker.Entity.Developer;
import com.BuildMaster.Project_Tracker.Service.DeveloperService;
import com.BuildMaster.Project_Tracker.repository.AuditLogRepository;
import com.BuildMaster.Project_Tracker.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImp implements DeveloperService {

    private final DeveloperRepository developerRepository;
    private final AuditLogRepository auditLogRepository;

    private void logAudit(String actionType, String entityType, String entityId, String actor, String payload) {
        AuditLog log = AuditLog.builder()
                .actionType(actionType)
                .entityType(entityType)
                .entityId(entityId)
                .actorName(actor)
                .payload(payload)
                .timestamp(LocalDateTime.now())
                .build();
        auditLogRepository.save(log);
    }

    @Override
    @Transactional
    public Developer createDeveloper(Developer developer) {
        Developer saved = developerRepository.save(developer);

        logAudit("CREATE", "Developer", saved.getId().toString(), "SYSTEM", saved.toString());
        return saved;
    }


    @Override
    @Transactional
    public Developer updateDeveloper(Long id, Developer developer) {
        Developer existing = developerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found"));
        existing.setName(developer.getName());
        existing.setEmail(developer.getEmail());
        existing.setSkill(developer.getSkill());
        Developer saved = developerRepository.save(existing);

        logAudit("UPDATE", "developer", saved.toString(), "SYSTEM", "{}");
        return saved;
    }

    @Override
    @Transactional
    public void deleteDeveloper(Long id) {
        developerRepository.deleteById(id);
        logAudit("DELETE", "developer", id.toString(), "SYSTEM", "{}");

    }
    @Override
    public Developer getDeveloperById(Long id) {
        return developerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Developer not found"));
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    @Override
    public List<Developer> getTopDevelopers() {
        return developerRepository.findTop5ByTasksCount();
    }

    @Override
    public Page<Developer> getAllDevelopers(int page, int size, String sortby, String direction){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortby).descending()
                : Sort.by(sortby).ascending();

        PageRequest pageable = PageRequest.of(page, size, sort);
        return developerRepository.findAll(pageable);
    }
}
