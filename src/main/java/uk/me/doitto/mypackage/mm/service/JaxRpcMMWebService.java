package uk.me.doitto.mypackage.mm.service;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

public class JaxRpcMMWebService extends ServletEndpointSupport implements MusicManagerWebServiceIf {

	// don't try to inject this - server.wsdd is not a Spring config file !
	private MusicManagerWebServiceIf musicManagerService;
	// get a reference directly from the application context instead . . .
    protected void onInit () {
        this.musicManagerService = (MusicManagerWebServiceIf)getApplicationContext().getBean("musicManagerService");
    }

	public String printAlbums () {
		return musicManagerService.printAlbums();
	}

	public String printTracks () {
		return musicManagerService.printTracks();
	}

	public String printArtists () {
		return musicManagerService.printArtists();
	}

	public String getAlbumDetails (String id) {
		return musicManagerService.getAlbumDetails(id);
	}

	public String getTrackDetails (String id) {
		return musicManagerService.getArtistDetails(id);
	}

	public String getArtistDetails (String id) {
		return musicManagerService.getTrackDetails(id);
	}
}
