package blackout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import unsw.blackout.BlackoutController;
import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.utils.Angle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static blackout.TestHelpers.assertListAreEqualIgnoringOrder;
import static blackout.TestHelpers.assertListAreNotEqualIgnoringOrder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;

@TestInstance(value = Lifecycle.PER_CLASS)

public class MyTests {
        // TODO: Add your own tests here
        // Task 1 test
        @Test
        public void addDevicesAndSatellite() {
                BlackoutController controller = new BlackoutController();

                // Creates 1 satellite and 3 devices
                // 2 devices are in view of the satellite
                // 1 device is out of view of the satellite
                controller.createSatellite("Satellite1", "StandardSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(340));
                controller.createDevice("Iphone", "HandheldDevice", Angle.fromDegrees(30));
                controller.createDevice("Samsung", "LaptopDevice", Angle.fromDegrees(180));
                controller.createDevice("Oppo", "DesktopDevice", Angle.fromDegrees(330));

                assertListAreEqualIgnoringOrder(Arrays.asList("Satellite1"), controller.listSatelliteIds());
                assertListAreEqualIgnoringOrder(Arrays.asList("Iphone", "Samsung", "Oppo"), controller.listDeviceIds());
        }

        @Test
        public void deleteOneDeviceAndSatellite() {
                BlackoutController controller = new BlackoutController();

                controller.createDevice("Iphone", "HandheldDevice", Angle.fromDegrees(30));
                controller.createDevice("Samsung", "LaptopDevice", Angle.fromDegrees(180));
                controller.createDevice("Oppo", "DesktopDevice", Angle.fromDegrees(330));

                controller.removeDevice("Samsung");
                assertListAreEqualIgnoringOrder(Arrays.asList("Iphone", "Oppo"), controller.listDeviceIds());

                controller.createSatellite("Satellite1", "StandardSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(340));
                controller.createSatellite("Satellite2", "StandardSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(180));
                controller.removeSatellite("Satellite1");
                assertListAreEqualIgnoringOrder(Arrays.asList("Satellite2"), controller.listSatelliteIds());

                controller.createSatellite("Satellite3", "StandardSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(180));
                assertListAreEqualIgnoringOrder(Arrays.asList("Satellite2", "Satellite3"),
                                controller.listSatelliteIds());
        }

        @Test
        public void deleteAllDevicesAndSatellite() {
                BlackoutController controller = new BlackoutController();

                controller.createSatellite("Satellite1", "StandardSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(340));
                controller.createDevice("Iphone", "HandheldDevice", Angle.fromDegrees(30));
                controller.createDevice("Samsung", "LaptopDevice", Angle.fromDegrees(180));
                controller.createDevice("Oppo", "DesktopDevice", Angle.fromDegrees(330));

                controller.removeDevice("Iphone");
                controller.removeDevice("Samsung");
                controller.removeDevice("Oppo");
                controller.removeSatellite("Satellite1");
                assertListAreEqualIgnoringOrder(Collections.emptyList(), controller.listSatelliteIds());
                assertListAreEqualIgnoringOrder(Collections.emptyList(), controller.listDeviceIds());
        }

        @Test
        public void deleteItemDoesNotExist() {
                BlackoutController controller = new BlackoutController();

                controller.createDevice("Iphone", "HandheldDevice", Angle.fromDegrees(30));
                controller.createDevice("Samsung", "LaptopDevice", Angle.fromDegrees(180));
                controller.createDevice("Oppo", "DesktopDevice", Angle.fromDegrees(330));

                controller.removeDevice("Xiaomi");
                assertListAreEqualIgnoringOrder(Arrays.asList("Iphone", "Samsung", "Oppo"), controller.listDeviceIds());
                assertListAreNotEqualIgnoringOrder(Collections.emptyList(), controller.listDeviceIds());
                controller.createSatellite("Satellite1", "StandardSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(340));
                controller.removeSatellite("Satellite2");
                assertListAreEqualIgnoringOrder(Arrays.asList("Satellite1"), controller.listSatelliteIds());
                assertListAreNotEqualIgnoringOrder(Collections.emptyList(), controller.listSatelliteIds());
        }

        @Test
        public void addSameDeviceAndSatellite() {
                BlackoutController controller = new BlackoutController();

                controller.createDevice("Iphone", "HandheldDevice", Angle.fromDegrees(30));
                controller.createDevice("Iphone", "HandheldDevice", Angle.fromDegrees(0));
                controller.createDevice("Iphone", "DesktopDevice", Angle.fromDegrees(0));
                System.out.println(controller.listDeviceIds());
                assertListAreEqualIgnoringOrder(Arrays.asList("Iphone"), controller.listDeviceIds());

                controller.createSatellite("Satellite1", "StandardSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(340));
                controller.createSatellite("Satellite1", "StandardSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(30));
                controller.createSatellite("Satellite1", "RelaySatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(0));
                assertListAreEqualIgnoringOrder(Arrays.asList("Satellite1"), controller.listSatelliteIds());

                controller.createDevice("Iphone", "LaptopDevice", Angle.fromDegrees(30));
                assertListAreEqualIgnoringOrder(Arrays.asList("Iphone"), controller.listDeviceIds());

                controller.createSatellite("Satellite1", "TeleportingSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(55));
                assertListAreEqualIgnoringOrder(Arrays.asList("Satellite1"), controller.listSatelliteIds());
        }

        @Test
        public void getInfoTest() {
                // Task 1
                BlackoutController controller = new BlackoutController();

                // Creates 1 device and add some files to it
                controller.createDevice("DeviceC", "DesktopDevice", Angle.fromDegrees(330));
                assertListAreEqualIgnoringOrder(Arrays.asList("DeviceC"), controller.listDeviceIds());
                assertEquals(new EntityInfoResponse("DeviceC", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice"), controller.getInfo("DeviceC"));

                controller.addFileToDevice("DeviceC", "Hello World", "My first file!");
                Map<String, FileInfoResponse> expected = new HashMap<>();
                expected.put("Hello World",
                                new FileInfoResponse("Hello World", "My first file!", "My first file!".length(), true));

                assertEquals(new EntityInfoResponse("DeviceC", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("DeviceC"));
        }

        @Test
        public void basicFileSupport() {
                // Task 1
                BlackoutController controller = new BlackoutController();

                // Creates 1 device and add some files to it
                controller.createDevice("Iphone", "DesktopDevice", Angle.fromDegrees(330));
                assertListAreEqualIgnoringOrder(Arrays.asList("Iphone"), controller.listDeviceIds());
                assertEquals(new EntityInfoResponse("Iphone", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice"), controller.getInfo("Iphone"));

                controller.addFileToDevice("Iphone", "Test", "My name is haha");
                Map<String, FileInfoResponse> expected = new HashMap<>();
                expected.put("Test", new FileInfoResponse("Test", "My name is haha", "My name is haha".length(), true));

                assertEquals(new EntityInfoResponse("Iphone", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("Iphone"));
        }

        @Test
        public void testFileSupport() {
                // Task 1
                BlackoutController controller = new BlackoutController();

                // Creates 1 device and add some files to it
                controller.createDevice("Macbook", "DesktopDevice", Angle.fromDegrees(330));
                assertListAreEqualIgnoringOrder(Arrays.asList("Macbook"), controller.listDeviceIds());
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice"), controller.getInfo("Macbook"));

                controller.addFileToDevice("Macbook", "Test", "My name is haha");
                Map<String, FileInfoResponse> expected = new HashMap<>();
                expected.put("Test", new FileInfoResponse("Test", "My name is haha", "My name is haha".length(), true));
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("Macbook"));

                controller.addFileToDevice("Macbook", "Test 2", "Second testing");
                expected.put("Test 2",
                                new FileInfoResponse("Test 2", "Second testing", "Second testing".length(), true));
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("Macbook"));

                controller.addFileToDevice("Macbook", "Test 3", "And yes im testing again");
                expected.put("Test 3", new FileInfoResponse("Test 3", "And yes im testing again",
                                "And yes im testing again".length(), true));
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("Macbook"));

                controller.addFileToDevice("Macbook", "Test 4", "My fourth file!");
                expected.put("Test 4",
                                new FileInfoResponse("Test 4", "My fourth file!", "My fourth file!".length(), true));
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("Macbook"));

                controller.addFileToDevice("Macbook", "Test 5", "My fifth file!");
        }

        @Test
        public void testFileSupport2() {
                // Task 1
                BlackoutController controller = new BlackoutController();

                // Creates 1 device and add some files to it
                controller.createDevice("Macbook", "DesktopDevice", Angle.fromDegrees(330));
                assertListAreEqualIgnoringOrder(Arrays.asList("Macbook"), controller.listDeviceIds());
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice"), controller.getInfo("Macbook"));

                controller.addFileToDevice("Macbook", "Test", "My name is haha");
                Map<String, FileInfoResponse> expected = new HashMap<>();
                expected.put("Test", new FileInfoResponse("Test", "My name is haha", "My name is haha".length(), true));
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("Macbook"));

                controller.addFileToDevice("Macbook", "Test 2", "Second testing");
                expected.put("Test 2",
                                new FileInfoResponse("Test 2", "Second testing", "Second testing".length(), true));
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("Macbook"));

                controller.addFileToDevice("Macbook", "Test 3", "And yes im testing again");
                expected.put("Test 3", new FileInfoResponse("Test 3", "And yes im testing again",
                                "And yes im testing again".length(), true));
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("Macbook"));

                controller.addFileToDevice("Macbook", "Test 4", "My fourth file!");
                expected.put("Test 4",
                                new FileInfoResponse("Test 4", "My fourth file!", "My fourth file!".length(), true));
                assertEquals(new EntityInfoResponse("Macbook", Angle.fromDegrees(330), RADIUS_OF_JUPITER,
                                "DesktopDevice", expected), controller.getInfo("Macbook"));
        }

        @Test
        public void testMovement() {
                // Task 2
                BlackoutController controller = new BlackoutController();

                controller.createSatellite("Satellite1", "StandardSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(340));
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(340), 100 + RADIUS_OF_JUPITER,
                                "StandardSatellite"), controller.getInfo("Satellite1"));
                controller.simulate();
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(337.95), 100 + RADIUS_OF_JUPITER,
                                "StandardSatellite"), controller.getInfo("Satellite1"));
        }

        @Test
        public void testRelayMovement() {
                // Task 2
                // Example from the specification
                BlackoutController controller = new BlackoutController();

                // Creates 1 satellite and 2 devices
                // Gets a device to send a file to a satellites and gets another device to download it.
                // StandardSatellites are slow and transfer 1 byte per minute.
                controller.createSatellite("Satellite1", "RelaySatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(180));

                // moves in negative direction
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(180), 100 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite1"));
                controller.simulate();
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(178.77), 100 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite1"));
                controller.simulate();
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(177.54), 100 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite1"));
                controller.simulate();
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(176.31), 100 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite1"));

                controller.simulate(5);
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(170.18), 100 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite1"));
                controller.simulate(24);
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(140.72), 100 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite1"));
                // edge case
                controller.simulate();
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(139.49), 100 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite1"));
                // coming back
                controller.simulate(1);
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(140.72), 100 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite1"));
                controller.simulate(5);
                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(146.85), 100 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite1"));
        }

        @Test
        public void testTeleportingMovement() {
                // Test for expected teleportation movement behaviour
                BlackoutController controller = new BlackoutController();

                controller.createSatellite("Satellite1", "TeleportingSatellite", 10000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(0));

                controller.simulate();
                Angle clockwiseOnFirstMovement = controller.getInfo("Satellite1").getPosition();
                controller.simulate();
                Angle clockwiseOnSecondMovement = controller.getInfo("Satellite1").getPosition();
                assertTrue(clockwiseOnSecondMovement.compareTo(clockwiseOnFirstMovement) == 1);

                // It should take 250 simulations to reach theta = 180.
                // Simulate until Satellite1 reaches theta=180
                controller.simulate(250);

                // Verify that Satellite1 is now at theta=0
                assertTrue(controller.getInfo("Satellite1").getPosition().toDegrees() % 360 == 0);
        }

        @Test
        public void testOutOfRange() {
                BlackoutController controller = new BlackoutController();

                controller.createSatellite("Satellite1", "StandardSatellite", 1000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(320));
                controller.createDevice("Macbook", "LaptopDevice", Angle.fromDegrees(310));

                assertEquals(Collections.emptyList(), controller.communicableEntitiesInRange("Macbook"));
        }

        @Test
        public void movementTest() {
                BlackoutController controller = new BlackoutController();

                controller.createSatellite("Satellite1", "StandardSatellite", 20000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(180));
                controller.createSatellite("Satellite2", "TeleportingSatellite", 20000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(190));
                controller.createSatellite("Satellite3", "RelaySatellite", 20000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(200));
                controller.createSatellite("Satellite4", "RelaySatellite", 20000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(300));

                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(180), 20000 + RADIUS_OF_JUPITER,
                                "StandardSatellite"), controller.getInfo("Satellite1"));
                assertEquals(new EntityInfoResponse("Satellite2", Angle.fromDegrees(190), 20000 + RADIUS_OF_JUPITER,
                                "TeleportingSatellite"), controller.getInfo("Satellite2"));
                assertEquals(new EntityInfoResponse("Satellite3", Angle.fromDegrees(200), 20000 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite3"));
                assertEquals(new EntityInfoResponse("Satellite4", Angle.fromDegrees(300), 20000 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite4"));

                controller.simulate(1);

                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(178.4068),
                                20000 + RADIUS_OF_JUPITER, "StandardSatellite"), controller.getInfo("Satellite1"));
                assertEquals(new EntityInfoResponse("Satellite2", Angle.fromDegrees(190.6372),
                                20000 + RADIUS_OF_JUPITER, "TeleportingSatellite"), controller.getInfo("Satellite2"));
                assertEquals(new EntityInfoResponse("Satellite3", Angle.fromDegrees(199.04412508736837),
                                20000 + RADIUS_OF_JUPITER, "RelaySatellite"), controller.getInfo("Satellite3"));
                assertEquals(new EntityInfoResponse("Satellite4", Angle.fromDegrees(299.0441),
                                20000 + RADIUS_OF_JUPITER, "RelaySatellite"), controller.getInfo("Satellite4"));

                controller.simulate(5);

                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(170.4412),
                                20000 + RADIUS_OF_JUPITER, "StandardSatellite"), controller.getInfo("Satellite1"));
                assertEquals(new EntityInfoResponse("Satellite2", Angle.fromDegrees(193.82349965052657),
                                20000 + RADIUS_OF_JUPITER, "TeleportingSatellite"), controller.getInfo("Satellite2"));
                assertEquals(new EntityInfoResponse("Satellite3", Angle.fromDegrees(194.26475052421023),
                                20000 + RADIUS_OF_JUPITER, "RelaySatellite"), controller.getInfo("Satellite3"));
                assertEquals(new EntityInfoResponse("Satellite4", Angle.fromDegrees(294.2647),
                                20000 + RADIUS_OF_JUPITER, "RelaySatellite"), controller.getInfo("Satellite4"));

                controller.simulate(10);

                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(154.51), 20000 + RADIUS_OF_JUPITER,
                                "StandardSatellite"), controller.getInfo("Satellite1"));
                assertEquals(new EntityInfoResponse("Satellite2", Angle.fromDegrees(200.1959),
                                20000 + RADIUS_OF_JUPITER, "TeleportingSatellite"), controller.getInfo("Satellite2"));
                assertEquals(new EntityInfoResponse("Satellite3", Angle.fromDegrees(184.70600139789394),
                                20000 + RADIUS_OF_JUPITER, "RelaySatellite"), controller.getInfo("Satellite3"));
                assertEquals(new EntityInfoResponse("Satellite4", Angle.fromDegrees(284.706), 20000 + RADIUS_OF_JUPITER,
                                "RelaySatellite"), controller.getInfo("Satellite4"));

                controller.simulate(60);

                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(58.9225), 20000 + RADIUS_OF_JUPITER,
                                "StandardSatellite"), controller.getInfo("Satellite1"));
                assertEquals(new EntityInfoResponse("Satellite2", Angle.fromDegrees(238.4309),
                                20000 + RADIUS_OF_JUPITER, "TeleportingSatellite"), controller.getInfo("Satellite2"));
                assertEquals(new EntityInfoResponse("Satellite3", Angle.fromDegrees(152.20625436841854),
                                20000 + RADIUS_OF_JUPITER, "RelaySatellite"), controller.getInfo("Satellite3"));
                assertEquals(new EntityInfoResponse("Satellite4", Angle.fromDegrees(227.35350663999617),
                                20000 + RADIUS_OF_JUPITER, "RelaySatellite"), controller.getInfo("Satellite4"));

                controller.simulate(120);

                assertEquals(new EntityInfoResponse("Satellite1", Angle.fromDegrees(227.7475),
                                20000 + RADIUS_OF_JUPITER, "StandardSatellite"), controller.getInfo("Satellite1"));
                assertEquals(new EntityInfoResponse("Satellite2", Angle.fromDegrees(314.9009),
                                20000 + RADIUS_OF_JUPITER, "TeleportingSatellite"), controller.getInfo("Satellite2"));
                assertEquals(new EntityInfoResponse("Satellite3", Angle.fromDegrees(165.58850314526134),
                                20000 + RADIUS_OF_JUPITER, "RelaySatellite"), controller.getInfo("Satellite3"));
                assertEquals(new EntityInfoResponse("Satellite4", Angle.fromDegrees(166.17751223157188),
                                20000 + RADIUS_OF_JUPITER, "RelaySatellite"), controller.getInfo("Satellite4"));
        }

        @Test
        public void testMultipleSatellitesInRange() {
                BlackoutController controller = new BlackoutController();
                controller.createSatellite("Satellite1", "StandardSatellite", 1000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(320));
                controller.createSatellite("Satellite2", "StandardSatellite", 1000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(315));
                controller.createDevice("Macbook", "LaptopDevice", Angle.fromDegrees(310));
                assertEquals(Arrays.asList("Satellite2"), controller.communicableEntitiesInRange("Macbook"));
                assertListAreEqualIgnoringOrder(Arrays.asList("Macbook", "Satellite1"),
                                controller.communicableEntitiesInRange("Satellite2"));
        }

        @Test
        public void testEntitiesViaRelay() {
                BlackoutController controller = new BlackoutController();

                controller.createSatellite("Satellite1", "StandardSatellite", 20000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(170));
                controller.createSatellite("Satellite2", "StandardSatellite", 20000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(250));
                controller.createSatellite("Satellite3", "RelaySatellite", 20000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(200));
                controller.createSatellite("Satellite4", "RelaySatellite", 130956, Angle.fromDegrees(213));
                controller.createDevice("DeviceA", "HandheldDevice", Angle.fromDegrees(180));

                assertListAreEqualIgnoringOrder(Arrays.asList("DeviceA", "Satellite2", "Satellite3", "Satellite4"),
                                controller.communicableEntitiesInRange("Satellite1"));
                assertListAreEqualIgnoringOrder(Arrays.asList("DeviceA", "Satellite1", "Satellite3", "Satellite4"),
                                controller.communicableEntitiesInRange("Satellite2"));
                assertListAreEqualIgnoringOrder(Arrays.asList("DeviceA", "Satellite1", "Satellite2", "Satellite4"),
                                controller.communicableEntitiesInRange("Satellite3"));
                assertListAreEqualIgnoringOrder(Arrays.asList("DeviceA", "Satellite1", "Satellite2", "Satellite3"),
                                controller.communicableEntitiesInRange("Satellite4"));
                assertListAreEqualIgnoringOrder(Arrays.asList("Satellite3", "Satellite1", "Satellite2", "Satellite4"),
                                controller.communicableEntitiesInRange("DeviceA"));
        }
}
