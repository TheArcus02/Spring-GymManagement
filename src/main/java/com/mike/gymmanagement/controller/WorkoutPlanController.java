package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.WorkoutPlan;
import com.mike.gymmanagement.service.WorkoutPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/workout-plan")
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    @Autowired
    public WorkoutPlanController(WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<WorkoutPlan>> getAllWorkoutPlans() {
        return ResponseEntity.ok().body(workoutPlanService.getAllWorkoutPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlanById(@PathVariable Long id) {
        WorkoutPlan workoutPlan = workoutPlanService.getWorkoutPlanById(id);
        if (workoutPlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(workoutPlan);
    }

    @PostMapping("")
    public WorkoutPlan addWorkoutPlan(@RequestBody @Valid WorkoutPlan workoutPlan) {
        return workoutPlanService.addWorkoutPlan(workoutPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlan> updateWorkoutPlan(@PathVariable Long id,
                                                         @RequestBody @Valid WorkoutPlan updatedWorkoutPlan) throws NotFoundException {
        WorkoutPlan workoutPlan = workoutPlanService.updateWorkoutPlan(id, updatedWorkoutPlan);
        if (workoutPlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(workoutPlan);
    }

    @PatchMapping("/{workoutPlanId}/training/{trainingId}")
    public ResponseEntity<WorkoutPlan> assignTraining(@PathVariable Long workoutPlanId, @PathVariable Long trainingId) throws NotFoundException {
        WorkoutPlan workoutPlan = workoutPlanService.assignTraining(workoutPlanId, trainingId);
        if (workoutPlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(workoutPlan);
    }
    
    @DeleteMapping("/{workoutPlanId}/training/{trainingId}")
    public ResponseEntity<WorkoutPlan> unassignTraining(@PathVariable Long workoutPlanId, @PathVariable Long trainingId) throws NotFoundException {
        WorkoutPlan workoutPlan = workoutPlanService.unassignTraining(workoutPlanId, trainingId);
        if (workoutPlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(workoutPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        workoutPlanService.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }


}
