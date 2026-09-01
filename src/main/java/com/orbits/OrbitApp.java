package com.orbits;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class OrbitApp extends Application {
    Simulation simulation;
    private final Map<Body, Sphere> sphereMap = new HashMap<>();
    private final Group root = new Group();
    private static final double positionScale = 1500;
    private static final double bodyScale= 1500; 

    @Override
    public void start(Stage stage){ 
        SolarSystem solarSystem= new SolarSystem();
        List<Body> bodies= solarSystem.getSystemBodies();
        this.simulation = new Simulation(bodies);
        javafx.scene.PointLight sunLight = new javafx.scene.PointLight(Color.WHITE);
        sunLight.setTranslateX(0);
        sunLight.setTranslateY(0);
        sunLight.setTranslateZ(0);
        root.getChildren().add(sunLight);

        for (Body b : bodies) {
            double radius = b.name.equals("sun")
            ? Math.cbrt(b.mass) * 350
            : Math.cbrt(b.mass) * bodyScale;
            Sphere sphere = new Sphere(radius, 64);
            String texturePath = "/" + b.name + ".jpg";
            Image texture = new Image(
                getClass().getResourceAsStream(texturePath)
            );
            PhongMaterial material = new PhongMaterial();
            if (b.name.equals("sun")) {
                material.setSelfIlluminationMap(texture);
                sunLight.getExclusionScope().add(sphere);
            }
            material.setDiffuseMap(texture);
            sphere.setMaterial(material);
            sphereMap.put(b, sphere);
            root.getChildren().add(sphere);
        }

        AmbientLight ambientLight = new AmbientLight(Color.rgb(40, 40, 40));
        root.getChildren().add(ambientLight);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                new Rotate(-80, Rotate.X_AXIS), 
                new javafx.scene.transform.Translate(0, 0, -2200) 
        );
        camera.setFarClip(5000);

        Scene scene = new Scene(root, 1600, 1200, true);
        Image stars = new Image(getClass().getResourceAsStream("/stars.jpg"));
        scene.setFill(new javafx.scene.paint.ImagePattern(stars, 0, 0, 1, 1, true));
        scene.setCamera(camera);
        stage.setScene(scene);
        stage.setTitle("3d orbit simulation");
        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                simulation.steps(0.0001); 
                for (Body b : bodies) {
                    Sphere s = sphereMap.get(b);
                    s.setTranslateX(b.positionVector.getX() * positionScale);
                    s.setTranslateY(-b.positionVector.getY() * positionScale); 
                    s.setTranslateZ(b.positionVector.getZ() * positionScale);
                }
            }
        }.start();
    }

    public static void main(String args[]){
        launch(args);
    }
}
