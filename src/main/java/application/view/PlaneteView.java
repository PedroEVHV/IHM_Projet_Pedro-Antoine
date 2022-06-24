package application.view;

import java.util.HashMap;
import java.util.Map;

import application.geohash.GeoHashHelper;
import application.geohash.Location;
import application.util.CameraManager;
import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.Spinner;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;


public class PlaneteView {
	
    //Controller controller;

    private boolean is3Don;

    private final Group geometry = new Group();
    private final Group root3D = new Group();
    
    private static final float TEXTURE_LAT_OFFSET = -0.2f;
    private static final float TEXTURE_LON_OFFSET = 2.8f;

    private SubScene subscene;

    HashMap<String, Integer> occForGeoHashList = new HashMap<>();
	int[] legend = new int[8];
	private Color[] colors = {Color.rgb(254, 240, 1), Color.rgb(255, 206, 3), Color.rgb(253, 154, 1), Color.rgb(253, 97, 4), Color.rgb(255, 44, 5), Color.rgb(240, 5, 5), Color.rgb(200, 4, 4)};
    
    public PlaneteView(/*Controller c*/) {
        //this.controller = c;
        this.is3Don = true;

    }
	
	public void loadEarth(Pane view3D) {
        // Load geometry
        ObjModelImporter objImporter = new ObjModelImporter();
        try {
            objImporter.read("src/main/java/application/data/earth.obj");
        } catch (ImportException e) {
            System.out.println(e.getMessage());
        }
        MeshView[] meshViews = objImporter.getImport();
        Group earth = new Group(meshViews);
        geometry.getChildren().add(earth);

        root3D.getChildren().add(geometry);

        // Add a camera group
        PerspectiveCamera camera = new PerspectiveCamera(true);
        new CameraManager(camera, view3D, root3D);

        // Add point light
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(-180);
        light.setTranslateY(-90);
        light.setTranslateZ(-120);
        light.getScope().addAll(root3D);
        root3D.getChildren().add(light);

        // Add ambient light
        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        ambientLight.getScope().addAll(root3D);
        root3D.getChildren().add(ambientLight);

        // Create scene
        SubScene subscene = new SubScene(root3D, 750, 560
                , true, SceneAntialiasing.BALANCED);
        subscene.setCamera(camera);
        subscene.setFill(Color.BLACK);
        view3D.getChildren().addAll(subscene);

        }
	
	void displayOccOnEarth2D() {
		Group geoHash = new Group();
        Sphere center1 = new Sphere(0.01);
        center1.setTranslateX(1);
        Sphere center2 = new Sphere(0.01);
        center2.setMaterial(new PhongMaterial(Color.GREEN));
        center2.setTranslateY(1);
        Sphere center3 = new Sphere(0.01);
        center3.setTranslateZ(1);
        center3.setMaterial(new PhongMaterial(Color.BLUEVIOLET));
        geoHash.getChildren().addAll(center1, center2, center3);
		for(Map.Entry<String, Integer> e : occForGeoHashList.entrySet()) {
        	Color c = Color.rgb(0, 0, 0);
        	for(int i = 0; i < 7; i++) {
        		if((e.getValue() > legend[i]) && (e.getValue() <= legend[i+1])) {

        			c = colors[i];
        		}
            }
            Location loc = GeoHashHelper.getLocation(e.getKey());

            if(is3Don) {
                geohash3D(geoHash, e.getValue(), (float) loc.lat(), (float)loc.lng(), c);
            } else {
                AddQuadrilateral(geoHash, geoCoordTo3dCoord((float) loc.lat()+ (float) 0.7, (float) loc.lng()+ (float) 0.7),geoCoordTo3dCoord((float) loc.lat()- (float) 0.7, (float) loc.lng()+ (float) 0.7), geoCoordTo3dCoord((float) loc.lat()- (float) 0.7, (float) loc.lng()- (float) 0.7),geoCoordTo3dCoord((float) loc.lat()+ (float) 0.7, (float) loc.lng()- (float) 0.7), new PhongMaterial(c));
            }
        }
		geoHash.setScaleZ(1.001);
		root3D.getChildren().add(geoHash);


	}
	
	private Point3D geoCoordTo3dCoord(float lat, float lon) {
        float lat_cor = lat + TEXTURE_LAT_OFFSET;
        float lon_cor = lon + TEXTURE_LON_OFFSET;
        return new Point3D(
                -Math.sin(Math.toRadians(lon_cor))
                        * Math.cos(Math.toRadians(lat_cor)),
                -Math.sin(Math.toRadians(lat_cor)),
                Math.cos(Math.toRadians(lon_cor))
                        * Math.cos(Math.toRadians(lat_cor)));
    }
	
	private void geohash3D(Group parent, int n, float lat, float lng, Color col) {
        /*
    	double h = Math.sqrt(Math.sqrt(n)); 
    	Box box = new Box(0.02, h*(0.05), 0.02);
        box.setMaterial(new PhongMaterial(col));
        Group box_grp = new Group(box);

        box_grp.setTranslateX(pos.getX());
        box_grp.setTranslateY(pos.getY());
        box_grp.setTranslateZ(pos.getZ());
        */
        Point3D pos = geoCoordTo3dCoord(lat, lng);


        //Rotation setup
        Point3D origin = new Point3D(0,0,0);
        Point3D anchor = new Point3D(1,0,0);

        double dx = pos.getX();
        double dy = pos.getY();
        double dz = pos.getZ();

        double h = Math.sqrt(Math.sqrt(n));

        dx += dx*h/10;
        dy += dy*h/10;
        dz += dz*h/10;


        Cylinder cylinder = createCylinder(new Segment(origin, new Point3D(dx, dy, dz)));
        cylinder.setMaterial(new PhongMaterial(col));



        Group cgrp = new Group();
        cgrp.getChildren().add(cylinder);

        parent.getChildren().add(cgrp);
    }

    static public Cylinder createCylinder(Segment segment) {
        // x axis vector is <1,0,0>
        // y axis vector is <0,1,0>
        // z axis vector is <0,0,1>
        // angle = arccos((P*Q)/(|P|*|Q|))
        // define a point representing the Y axis
        Point3D yAxis = new Point3D(0,1,0);
        // define a point based on the difference of our end point from the start point of our segment
        Point3D seg = segment.getEnd().subtract(segment.getStart());
        // determine the length of our line or the height of our cylinder object
        double height = seg.magnitude();
        // get the midpoint of our line segment
        Point3D midpoint = segment.getEnd().midpoint(segment.getStart());
        // set up a translate transform to move to our cylinder to the midpoint
        Translate moveToMidpoint = new Translate(midpoint.getX(), midpoint.getY(), midpoint.getZ());
        // get the axis about which we want to rotate our object
        Point3D axisOfRotation = seg.crossProduct(yAxis);
        // get the angle we want to rotate our cylinder
        double angle = Math.acos(seg.normalize().dotProduct(yAxis));
        // create our rotating transform for our cylinder object
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);
        // create our cylinder object representing our line
        Cylinder line = new Cylinder(0.01, height);
        // add our two transfroms to our cylinder object
        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        return line;
    } // end of the createCylinder method
	
	private void AddQuadrilateral(Group parent, Point3D topRight, Point3D bottomRight, Point3D bottomLeft, Point3D topLeft, PhongMaterial material) {
    	final TriangleMesh triangleMesh = new TriangleMesh();
    	
    	final float[] points = {
    			(float)topRight.getX(), (float)topRight.getY(), (float)topRight.getZ(),
    			(float)topLeft.getX(), (float)topLeft.getY(), (float)topLeft.getZ(),
    			(float)bottomLeft.getX(), (float)bottomLeft.getY(), (float)bottomLeft.getZ(),
    			(float)bottomRight.getX(), (float)bottomRight.getY(), (float)bottomRight.getZ()
    		};
    	final float[] texCoords = {
    			1, 1,
    			1, 0,
    			0, 1,
    			0, 0
    		};

    	final int[] faces = {
    			0, 1, 1, 0, 2, 2,
    			0, 1, 2, 2, 3, 3
    		};
    	
    	triangleMesh.getPoints().setAll(points);
    	triangleMesh.getTexCoords().setAll(texCoords);
    	triangleMesh.getFaces().setAll(faces);
    	
    	final MeshView meshView = new MeshView(triangleMesh);
    	meshView.setMaterial(material);
    	parent.getChildren().addAll(meshView);
    }

}
