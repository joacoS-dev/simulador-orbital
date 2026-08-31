package com.orbits;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class OrbitApp extends Application {
    Simulation simulation;
    private final Map<Body, Sphere> sphereMap = new HashMap<>();
    private final Group root = new Group();

    @Override
    public void start(Stage stage){ 
        List<Body> bodies= new ArrayList<>();
        this.simulation = new Simulation(bodies);

        for (Body b : bodies) {
            Sphere sphere = new Sphere(Math.cbrt(b.mass) * 2, 64);
            PhongMaterial material = new PhongMaterial();

            material.setDiffuseColor(javafx.scene.paint.Color.rgb(
                    (b.color >> 16) & 0xFF, (b.color >> 8) & 0xFF, b.color & 0xFF));

            sphere.setMaterial(material);
            sphereMap.put(b, sphere);
            root.getChildren().add(sphere);
        }
        
        //ambient light
        javafx.scene.AmbientLight ambientLight = new javafx.scene.AmbientLight(
        javafx.scene.paint.Color.color(0.2, 0.2, 0.2));
        root.getChildren().add(ambientLight);

        //sun light
        javafx.scene.PointLight sunLight = new javafx.scene.PointLight(javafx.scene.paint.Color.WHITE);
        sunLight.setTranslateX(0);
        sunLight.setTranslateY(0);
        sunLight.setTranslateZ(0);
        root.getChildren().add(sunLight);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                new Rotate(-60, Rotate.X_AXIS), 
                new javafx.scene.transform.Translate(0, 0, -2200) 
        );
        camera.setFarClip(5000);

        Scene scene = new Scene(root, 1600, 1200, true);
        scene.setCamera(camera);
        stage.setScene(scene);
        stage.setTitle("3d orbit simulation");
        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                simulation.steps(0.1); 
                for (Body b : bodies) {
                    Sphere s = sphereMap.get(b);
                    s.setTranslateX(b.positionVector.getX());
                    s.setTranslateY(-b.positionVector.getY()); 
                    s.setTranslateZ(b.positionVector.getZ());
                }
            }
        }.start();
    }

    public static void main(String args[]){
        launch(args);
    }
}
