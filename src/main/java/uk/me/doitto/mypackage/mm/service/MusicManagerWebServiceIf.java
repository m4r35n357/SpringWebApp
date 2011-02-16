package uk.me.doitto.mypackage.mm.service;

public interface MusicManagerWebServiceIf {
	String printAlbums ();
	String printTracks ();
	String printArtists ();
	String getAlbumDetails (String id);
	String getTrackDetails (String id);
	String getArtistDetails (String id);
}
