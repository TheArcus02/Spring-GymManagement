package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.model.WorkoutPlan;
import com.mike.gymmanagement.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public WorkoutPlan addWorkoutPlan(@RequestBody WorkoutPlan workoutPlan) {
        return workoutPlanService.addWorkoutPlan(workoutPlan);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WorkoutPlan> updateWorkoutPlan(@PathVariable Long id, @RequestBody WorkoutPlan updatedWorkoutPlan) {
        WorkoutPlan workoutPlan = workoutPlanService.updateWorkoutPlan(id, updatedWorkoutPlan);
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
