package uk.me.doitto.mypackage.mm.service;

import java.util.List;
import java.util.Map;

import uk.me.doitto.mypackage.mm.object.Album;
import uk.me.doitto.mypackage.mm.object.Artist;
import uk.me.doitto.mypackage.mm.object.Track;

public interface MusicManagerServiceIf {
	void createAlbum (String title);
	void createTrack (String title);
	void createArtist (String name);
	
	void createAlbum (Album album);
	void createTrack (Track track);	
	void createArtist (Artist artist);
	
	void saveAlbum (Album album);
	void saveTrack (Track track);
	void saveArtist (Artist artist);
	
	void deleteAlbum (Album album);
	void deleteTrack (Track track);
	void deleteArtist (Artist artist);
	
	Album getAlbum (String albumId);
	Track getTrack (String trackId);
	Artist getArtist (String artistId);
	
	List<Album> getAllAlbums ();
	List<Track> getAllTracks ();
	List<Artist> getAllArtists ();
	
	Map<String,String> getAlbumTitleIdMap ();
	Map<String,String> getTrackTitleIdMap ();
	Map<String,String> getArtistNameIdMap ();
	
	void linkTrackToAlbum (String trackId, String albumId);
	void unlinkTrackFromAlbum (String trackId, String albumId);
	void linkArtistToAlbum (String artistId, String albumId);
	void unlinkArtistFromAlbum (String artistId, String albumId);
	
	void linkPerformerToTrack (String performerId, String trackId);
	void unlinkPerformerFromTrack (String performerId, String trackId);
//	void linkWriterToTrack (String writerId, String trackId);
//	void unlinkWriterFromTrack (String writerId, String trackId);
	
	void linkMemberToBand (String memberId, String bandId);
	void unlinkMemberFromBand (String memberId, String bandId);
	void linkBandToMember (String bandId, String memberId);
	void unlinkBandFromMember (String bandId, String memberId);
}
