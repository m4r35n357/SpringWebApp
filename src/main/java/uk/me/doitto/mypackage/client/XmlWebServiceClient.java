package uk.me.doitto.mypackage.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.RemoteConnectFailureException;

import uk.me.doitto.mypackage.mm.object.Album;
import uk.me.doitto.mypackage.mm.service.MusicManagerServiceIf;
import uk.me.doitto.mypackage.mm.service.MusicManagerWebServiceIf;
import uk.me.doitto.mypackage.service.DwrGlobalWebServiceIf;

public class XmlWebServiceClient {
	
	ApplicationContext applicationContext = new FileSystemXmlApplicationContext("WEB-INF/src/uk/me/doitto/mypackage/client/clientContext.xml");
	
	DwrGlobalWebServiceIf clockWebServiceHttpInvokerProxy = (DwrGlobalWebServiceIf)applicationContext.getBean("clockWebServiceHttpInvokerProxy");
	DwrGlobalWebServiceIf clockWebServiceHessianProxy = (DwrGlobalWebServiceIf)applicationContext.getBean("clockWebServiceHessianProxy");
	DwrGlobalWebServiceIf clockWebServiceBurlapProxy = (DwrGlobalWebServiceIf)applicationContext.getBean("clockWebServiceBurlapProxy");
	DwrGlobalWebServiceIf clockWebServiceJaxRpcProxy = (DwrGlobalWebServiceIf)applicationContext.getBean("clockWebServiceJaxRpcProxy");
	
	MusicManagerWebServiceIf musicManagerWebServiceHttpInvokerProxy = (MusicManagerWebServiceIf)applicationContext.getBean("musicManagerWebServiceHttpInvokerProxy");
	MusicManagerWebServiceIf musicManagerWebServiceJaxRpcProxy = (MusicManagerWebServiceIf)applicationContext.getBean("musicManagerWebServiceJaxRpcProxy");
	
	MusicManagerServiceIf musicManagerServiceHttpInvokerProxy = (MusicManagerServiceIf)applicationContext.getBean("musicManagerServiceHttpInvokerProxy");

	private static final String albumId = "8a80807e1374724a01137643d4db0001";
	
	public XmlWebServiceClient () {
		System.out.println(" *** clockWebServiceHttpInvokerProxy *** ");
		try {
//			System.out.println(clockWebServiceHttpInvokerProxy.hello());
//			System.out.println(clockWebServiceHttpInvokerProxy.echo("hi!!"));
		} catch (RemoteConnectFailureException e) {
			System.out.println("Connection failed to remote host!");
		} catch (RemoteAccessException e) {
			System.out.println("Access denied by remote host!");
		}
		System.out.println(" *** clockWebServiceHttpInvokerProxy *** ");
		System.out.println("");
		
		System.out.println(" *** clockWebServiceHessianProxy *** ");
		try {
//			System.out.println(clockWebServiceHessianProxy.hello());
//			System.out.println(clockWebServiceHessianProxy.echo("hi!!"));
		} catch (RemoteConnectFailureException e) {
			System.out.println("Connection failed to remote host!");
		} catch (RemoteAccessException e) {
			System.out.println("Access denied by remote host!");
		}
		System.out.println(" *** clockWebServiceHessianProxy *** ");
		System.out.println("");
		
		System.out.println(" *** clockWebServiceBurlapProxy *** ");
		try {
//			System.out.println(clockWebServiceBurlapProxy.hello());
//			System.out.println(clockWebServiceBurlapProxy.echo("hi!!"));
		} catch (RemoteConnectFailureException e) {
			System.out.println("Connection failed to remote host!");
		} catch (RemoteAccessException e) {
			System.out.println("Access denied by remote host!");
		}
		System.out.println(" *** clockWebServiceBurlapProxy *** ");
		System.out.println("");
		
		System.out.println(" *** clockWebServiceJaxRpcProxy *** ");
		try {
//			System.out.println(clockWebServiceJaxRpcProxy.hello());
//			System.out.println(clockWebServiceJaxRpcProxy.echo("hi!!"));
		} catch (RemoteConnectFailureException e) {
			System.out.println("Connection failed to remote host!");
		} catch (RemoteAccessException e) {
			System.out.println("Access denied by remote host!");
		}
		System.out.println(" *** clockWebServiceJaxRpcProxy *** ");
		System.out.println("");
		
		System.out.println(" *** musicManagerWebServiceHttpInvokerProxy *** ");
		try {
//			System.out.println(musicManagerWebServiceHttpInvokerProxy.hello());
//			System.out.println(musicManagerWebServiceHttpInvokerProxy.echo("hi!!"));
			System.out.println(musicManagerWebServiceHttpInvokerProxy.printAlbums());
			System.out.println(musicManagerWebServiceHttpInvokerProxy.printTracks());
			System.out.println(musicManagerWebServiceHttpInvokerProxy.printArtists());
			System.out.println(musicManagerWebServiceHttpInvokerProxy.getAlbumDetails(albumId));
		} catch (RemoteConnectFailureException e) {
			System.out.println("Connection failed to remote host!");
		} catch (RemoteAccessException e) {
			System.out.println("Access denied by remote host!");
		}
		System.out.println(" *** musicManagerWebServiceHttpInvokerProxy *** ");
		System.out.println("");
		
		System.out.println(" *** musicManagerWebServiceJaxRpcProxy *** ");
		try {
//			System.out.println(musicManagerWebServiceJaxRpcProxy.hello());
//			System.out.println(musicManagerWebServiceJaxRpcProxy.echo("hi!!"));
			System.out.println(musicManagerWebServiceJaxRpcProxy.printAlbums());
			System.out.println(musicManagerWebServiceJaxRpcProxy.printTracks());
			System.out.println(musicManagerWebServiceJaxRpcProxy.printArtists());
			System.out.println(musicManagerWebServiceJaxRpcProxy.getAlbumDetails(albumId));
		} catch (RemoteConnectFailureException e) {
			System.out.println("Connection failed to remote host!");
		} catch (RemoteAccessException e) {
			System.out.println("Access denied by remote host!");
		}
		System.out.println(" *** musicManagerWebServiceJaxRpcProxy *** ");
		System.out.println("");
		
		System.out.println(" *** musicManagerServiceHttpInvokerProxy *** ");
		Album album = musicManagerServiceHttpInvokerProxy.getAlbum(albumId);
		System.out.println(album.toString());
		System.out.println(album.getDetails());
		System.out.println(" *** musicManagerServiceHttpInvokerProxy *** ");
		System.out.println("");
	}

	public static void main (String[] args) {
		new XmlWebServiceClient();
	}
}
