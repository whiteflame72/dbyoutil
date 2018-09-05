package org.apache.tool.db;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.LinkedHashMap;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.apache.commons.collections.map.ListOrderedMap;

public class DBAlert {

	private static ListOrderedMap sqlMap = new ListOrderedMap();
	
	public static void main(String[] args) {
		final TrayIcon trayIcon;

		if (SystemTray.isSupported()) {

			SystemTray tray = SystemTray.getSystemTray();
			File f = new File(".");
			String path = f.getAbsolutePath();
			Image image = Toolkit.getDefaultToolkit().getImage(f.getPath() + "/critical_error.gif");

			MouseListener mouseListener = new MouseListener() {

				public void mouseClicked(MouseEvent e) {
					System.out.println("Tray Icon - Mouse clicked!");
				}

				public void mouseEntered(MouseEvent e) {
					System.out.println("Tray Icon - Mouse entered!");
				}

				public void mouseExited(MouseEvent e) {
					System.out.println("Tray Icon - Mouse exited!");
				}

				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				public void mouseReleased(MouseEvent e) {
					System.out.println("Tray Icon - Mouse released!");
				}
			};

			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting...");
					System.exit(0);
				}
			};

			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);

			trayIcon = new TrayIcon(image, "DB Alert", popup);

			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					trayIcon.displayMessage("Action Event",
							"An Action Event Has Been Performed!",
							TrayIcon.MessageType.INFO);
				}
			};

			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(actionListener);
			trayIcon.addMouseListener(mouseListener);

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.err.println("TrayIcon could not be added.");
			}

			while(true) {
				System.out.println("------------------ DBAlert 1.0 ------------------");
				StoreDB.setSqlMap(sqlMap);
				StoreDB.main(null);
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} else {
			// System Tray is not supported
			System.err.println("TrayIcon is not supported.");
		}
	}
}
