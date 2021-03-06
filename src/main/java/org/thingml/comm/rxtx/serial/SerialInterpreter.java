package org.thingml.comm.rxtx.serial;

import org.kevoree.*;
import org.kevoree.Package;
import org.kevoree.factory.DefaultKevoreeFactory;
import org.kevoree.factory.KevoreeFactory;
import org.kevoree.log.Log;

/**
 * Created by leiko on 11/02/15.
 */
public class SerialInterpreter implements SerialObserver {

    private KevoreeFactory factory = new DefaultKevoreeFactory();

    public void receive(String data) {
        Log.info("{} PROMPT " + data);
        if (!data.startsWith("HEADS->norm>")) {

            if (data.equals("Listing of supported task types")) {
                Package p = factory.createPackage();
                p.setName("sintef");

                org.kevoree.DeployUnit du = factory.createDeployUnit();
                du.setName("sintefnodetype");
                du.setVersion("1.0.0");
                p.addDeployUnits(du);

                TypeDefinition tf = factory.createNodeType();
                tf.setName("sintefnodetype");
                tf.setVersion("1.0.0");
                tf.addDeployUnits(du);

                p.addTypeDefinitions(tf);

                org.kevoree.DeployUnit du1 = factory.createDeployUnit();
                du1.setName("sintefchannel");
                du1.setVersion("1.0.0");
                p.addDeployUnits(du1);

                ChannelType tf1 = factory.createChannelType();
                tf1.setName("sintefchannel");
                tf1.setVersion("1.0.0");
                tf1.addDeployUnits(du1);

                p.addTypeDefinitions(tf);
                p.addTypeDefinitions(tf1);

                ContainerNode n = factory.createContainerNode();
                n.setStarted(true);
                n.setName("MySintefNode");
                n.setTypeDefinition(tf);
//                r = factory.createModelCloner().clone(modelService.getCurrentModel().getModel());
//                factory.root(r);
//
//                r.addPackages(p);
//                r.addNodes(n);
//                n.setGroups(r.getGroups());

            }

            if (data.equals("Listing of channels connected to current class instances")) {
                // TODO
            }
            //Task type=sender instance=tx state=STARTED
            //Task type=rxlcd instance=rcv state=STARTED
            //Taskinstance=tx port=0 ==> Taskinstance=rcv port=0
            //Taskinstance=rcv port=0 ==> Taskinstance=tx port=0

//            if (data.startsWith("Taskinstance=") && data.contains("port=")) {
//                String[] s1 = data.replace("Taskinstance=", "").replace("port=", "").replace("==>", "").split(" ");
//                String tx_iid = s1[0];
//                String tx_port_id = "tx" + s1[1];
//                String rcv_iid = s1[3];
//                String rcv_port_id = "rcv" + s1[4];
//                Log.info("{} Got channel string<" + data + ">");
//                Log.info("{} Parsed to<" + tx_iid + "><" + tx_port_id + "><" + rcv_iid + "><" + rcv_port_id + ">");
//
//                ComponentInstance tx_instance = r.findNodesByID("MySintefNode").findComponentsByID(tx_iid);
//                ComponentInstance rcv_instance = r.findNodesByID("MySintefNode").findComponentsByID(rcv_iid);
//                if ((tx_instance != null) && (rcv_instance != null)) {
//                    //PortTypeRef tx_port_ref = null;
//                    Port tx_port = tx_instance.findRequiredByID(tx_port_id);
//                    //if (tx_port != null) tx_port_ref = tx_port.getPortTypeRef();
//
//                    //PortTypeRef rcv_port_ref = null;
//                    Port rcv_port = rcv_instance.findProvidedByID(rcv_port_id);
//                    //if (rcv_port != null) rcv_port_ref = rcv_port.getPortTypeRef();
//
//                    if ((tx_port != null) && (rcv_port != null)) {
//                        // Do something
//                        String channel_name = "" + tx_iid + "_" + tx_port_id + "_" + rcv_iid + "_" + rcv_port_id;  // TODO  make this convention more robust
//                        Log.info("{} Try to find a channel with name <" + channel_name + ">");
//
//                        Channel ch_instance = r.findHubsByID(channel_name);
//                        if (ch_instance == null) {
//                            Log.info("{} Found component and ports ... creating channel <" + channel_name + ">");
//                            ch_instance = factory.createChannel();
//                            ch_instance.setName(channel_name);
//                            ch_instance.setTypeDefinition(p.findTypeDefinitionsByID("sintefchannel"));
//
//                            MBinding mb = factory.createMBinding();
//                            mb.setHub(ch_instance);
//                            mb.setPort(tx_port);
//
//                            ch_instance.addBindings(mb);
//
//                            MBinding mb2 = factory.createMBinding();
//                            mb2.setHub(ch_instance);
//                            mb2.setPort(rcv_port);
//                            ch_instance.addBindings(mb2);
//
//                            r.addMBindings(mb);
//                            r.addMBindings(mb2);
//                            r.addHubs(ch_instance);
//                        } else {
//                            Log.info("{} Found channel named <" + ch_instance.getName() + "> object:" + ch_instance);
//                        }
//                        // Find if channel
//                    } else {
//                        Log.info("{} Component ports not found ... channel skipped");
//                        Log.info("{} tx_port=" + tx_port);
//                        Log.info("{} rcv_port=" + rcv_port);
//                    }
//                } else {
//                    Log.info("{} Component instances not found ... channel skipped");
//                    Log.info("{} tx_instance=" + tx_instance);
//                    Log.info("{} rcv_instance=" + rcv_instance);
//                }
//            }
//
//            if (data.startsWith("Task type=") && data.contains("instance=")) {
//                String[] s1 = data.replace("Task type=", "").replace("instance=", "").replace("state=", "").split(" ");
//                String tid = s1[0];
//                String iid = s1[1];
//                boolean started = s1[2].equals("STARTED");
//                TypeDefinition t = r.findPackagesByID("sintef").findTypeDefinitionsByID(tid);
//                ComponentInstance instance = r.findNodesByID("MySintefNode").findComponentsByID(iid);
//
//                if (t == null) {
//                    t = factory.createTypeDefinition();
//                    t.setName(tid);
//
//                    r.findPackagesByID("sintef").addTypeDefinitions(t);
//                }
//                if (instance == null) {
//                    instance = factory.createComponentInstance();
//                    instance.setTypeDefinition(t);
//                    instance.setName(iid);
//
//                    r.findNodesByID("MySintefNode").addComponents(instance);
//                }
//
//                Log.info("{} Type def=" + t);
//                Log.info("{} Type inst=" + instance);
//
//            }
//
//            if (data.startsWith("Task type=") && !data.contains("instance=")) {
//                String componentTypeName = data.subSequence(
//                        data.indexOf("=") + 1, data.length() - 1).toString();
//                org.kevoree.DeployUnit du1 = factory.createDeployUnit();
//                du1.setName("sintef" + componentTypeName);
//                du1.setVersion("1.0.0");
//                p.addDeployUnits(du1);
//
//                org.kevoree.ComponentType tf1 = factory.createComponentType();
//                tf1.setName(componentTypeName);
//                tf1.setVersion("1.0.0");
//                tf1.addDeployUnits(du1);
//
//                for (int j = 0; j < 6; j++) {
//                    PortTypeRef porttyperef = factory.createPortTypeRef();
//                    porttyperef.setName("rcv" + j);
//                    porttyperef.setOptional(true);
//
//                    tf1.addProvided(porttyperef);
//
//                    PortTypeRef porttyperef1 = factory.createPortTypeRef();
//                    porttyperef1.setName("tx" + j);
//                    porttyperef1.setOptional(true);
//                    tf1.addRequired(porttyperef1);
//                }
//
//                p.addTypeDefinitions(tf1);
//
//                Log.info("{} Component type= " + tf1);
//            }
//            if (data.equals("End of listing") && r != null) {
//                modelService.update(r, new UpdateCallback() {
//                    public void run(Boolean applied) {
//
//                    }
//                });
//            }

        }
    }
}
