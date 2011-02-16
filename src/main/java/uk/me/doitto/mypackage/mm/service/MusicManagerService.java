package uk.me.doitto.mypackage.mm.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import uk.me.doitto.mypackage.admin.service.OwnerServiceIf;
import uk.me.doitto.mypackage.dao.GenericDAOIf;
import uk.me.doitto.mypackage.globals.Constants;
import uk.me.doitto.mypackage.mm.object.Album;
import uk.me.doitto.mypackage.mm.object.Artist;
import uk.me.doitto.mypackage.mm.object.Track;

/**
 * Classes to support various user interfaces to the Music Manager
 * 
 * @author Ian Smith
 *
 */
public class MusicManagerService implements MusicManagerServiceIf,MusicManagerWebServiceIf {
	
	private final Log log = LogFactory.getLog(getClass());
	
	private OwnerServiceIf ownerService;
	public void setOwnerService(OwnerServiceIf ownerService) {
		this.ownerService = ownerService;
	}
	
	private GenericDAOIf<Album> albumDAO;
	public void setAlbumDAO (GenericDAOIf<Album> albumDAO) {
		this.albumDAO = albumDAO;
	}

	private GenericDAOIf<Track> trackDAO;
	public void setTrackDAO (GenericDAOIf<Track> trackDAO) {
		this.trackDAO = trackDAO;
	}

	private GenericDAOIf<Artist> artistDAO;
	public void setArtistDAO (GenericDAOIf<Artist> artistDAO) {
		this.artistDAO = artistDAO;
	}

//	private GenericDAOIf<? extends PersistentClass> genericDAO;
	
	public void createAlbum (String title) {
		log.debug(title);
		albumDAO.persist(new Album(title, ownerService.getCurrentOwnerId()));
	}

	public void createTrack (String title) {
		log.debug(title);
		trackDAO.persist(new Track(title, ownerService.getCurrentOwnerId()));
	}

	public void createArtist (String name) {
		log.debug(name);
		artistDAO.persist(new Artist(name, ownerService.getCurrentOwnerId()));
	}

	public void createAlbum (Album album) {
		log.debug(album.getTitle());
		albumDAO.persist(album);
	}

	public void createTrack (Track track) {
		log.debug(track.getTitle());
		trackDAO.persist(track);
	}

	public void createArtist (Artist artist) {
		log.debug(artist.getName());
		artistDAO.persist(artist);
	}

	public void saveAlbum (Album album) {
		log.debug(album.toString());
		albumDAO.rePersist(album);
	}

	public void saveTrack (Track track) {
		log.debug(track.toString());
		trackDAO.rePersist(track);
	}

	public void saveArtist (Artist artist) {
		log.debug(artist.toString());
		artistDAO.rePersist(artist);
	}

	public void deleteAlbum (Album album) {
		if (! album.isLinked()) {
			log.debug(album.toString());
			albumDAO.delete(album);
		}
	}

	public void deleteTrack (Track track) {
		if (! track.isLinked()) {
			log.debug(track.toString());
			trackDAO.delete(track);
		}
	}

	public void deleteArtist (Artist artist) {
		if (! artist.isLinked()) {
			log.debug(artist.toString());
			artistDAO.delete(artist);
		}
	}

	public Album getAlbum (String albumId) {
		log.debug(albumId);
		return albumDAO.findById(albumId);
	}

	public Track getTrack (String trackId) {
		log.debug(trackId);
		return trackDAO.findById(trackId);
	}

	public Artist getArtist (String artistId) {
		log.debug(artistId);
		return artistDAO.findById(artistId);
	}

	public List<Album> getAllAlbums () {
		log.debug("");
		return albumDAO.findAll();
	}

	public List<Track> getAllTracks () {
		log.debug("");
		return trackDAO.findAll();
	}

	public List<Artist> getAllArtists () {
		log.debug("");
		return artistDAO.findAll();
	}

	public List<Album> getAlbumsByOwner () {
		log.debug("");
		Album album = new Album();
		album.setOwnerId(ownerService.getCurrentOwnerId());
		return albumDAO.findByExample(album);
	}
	
	public List<Track> getTracksByOwner () {
		log.debug("");
		Track track = new Track();
		track.setOwnerId(ownerService.getCurrentOwnerId());
		return trackDAO.findByExample(track);
	}

	public List<Artist> getArtistsByOwner () {
		log.debug("");
		Artist artist = new Artist();
		artist.setOwnerId(ownerService.getCurrentOwnerId());
		return artistDAO.findByExample(artist);
	}

//	public <S extends PersistentClass> Map<String, String> getIdNameMap (Class<S> clazz, Collection<S> collection) {
//		log.debug("");
//		Map<String, String> map = Collections.synchronizedMap(new TreeMap<String, String>());
//		for (S s : genericDAO.findAllBySubClass(clazz)) {
//			map.put(s.getName(), s.getId());
//		}
//		return map;
//	}

	public Map<String, String> getAlbumTitleIdMap () {
		log.debug("");
		Map<String, String> albumTitleMap = Collections.synchronizedMap(new TreeMap<String, String>());
		for (Album album : getAllAlbums()) {
			albumTitleMap.put(album.getTitle(), album.getId());
		}
		return albumTitleMap;
	}

	public Map<String, String> getTrackTitleIdMap () {
		log.debug("");
		Map<String, String> trackTitleMap = Collections.synchronizedMap(new TreeMap<String, String>());
		for (Track track : getAllTracks()) {
			trackTitleMap.put(track.getTitle(), track.getId());
		}
		return trackTitleMap;
	}

	public Map<String, String> getArtistNameIdMap () {
		log.debug("");
		Map<String, String> artistNameMap = Collections.synchronizedMap(new TreeMap<String, String>());
		for (Artist artist : getAllArtists()) {
			artistNameMap.put(artist.getName(), artist.getId());
		}
		return artistNameMap;
	}

	public void linkTrackToAlbum (String trackId, String albumId) {
		log.debug(trackId + ", " + albumId);
		getAlbum(albumId).addToTrackListing(getTrack(trackId));
	}
	
	public void unlinkTrackFromAlbum (String trackId, String albumId) {
		log.debug(trackId + ", " + albumId);
		getAlbum(albumId).removeFromTrackListing(getTrack(trackId));
	}

	public void linkArtistToAlbum (String artistId, String albumId) {
		log.debug(artistId + ", " + albumId);
		getAlbum(albumId).addToArtists(getArtist(artistId));
	}

	public void unlinkArtistFromAlbum (String artistId, String albumId) {
		log.debug(artistId + ", " + albumId);
		getAlbum(albumId).removeFromArtists(getArtist(artistId));
	}

	public void linkPerformerToTrack (String performerId, String trackId) {
		log.debug(performerId + ", " + trackId);
		getTrack(trackId).addToPerformers(getArtist(performerId));
	}

	public void unlinkPerformerFromTrack (String performerId, String trackId) {
		log.debug(performerId + ", " + trackId);
		getTrack(trackId).removeFromPerformers(getArtist(performerId));
	}

//	public void linkWriterToTrack (String writerId, String trackId) {
//		log.debug(writerId + ", " + trackId);
//		getTrack(trackId).addToWriters(getArtist(writerId));
//	}
//
//	public void unlinkWriterFromTrack (String writerId, String trackId) {
//		log.debug(writerId + ", " + trackId);
//		getTrack(trackId).removeFromWriters(getArtist(writerId));
//	}

	public void linkMemberToBand (String memberId, String bandId) {
		log.debug(memberId + ", " + bandId);
		if (! memberId.equals(bandId)) {
			getArtist(bandId).addToMembers(getArtist(memberId));
		}
	}

	public void unlinkMemberFromBand (String memberId, String bandId) {
		log.debug(memberId + ", " + bandId);
		getArtist(bandId).removeFromMembers(getArtist(memberId));
	}

	public void linkBandToMember (String bandId, String memberId) {
		log.debug(bandId + ", " + memberId);
		if (bandId.equals(memberId)) {
			getArtist(memberId).addToBands(getArtist(bandId));
		}
	}
	
	public void unlinkBandFromMember (String bandId, String memberId) {
		log.debug(bandId + ", " + memberId);
		getArtist(memberId).removeFromBands(getArtist(bandId));
	}
	
	public String printAlbums () {
		StringBuilder stringBuilder = new StringBuilder();
		for (Album album : getAllAlbums()) {
			stringBuilder.append(album.toString());
			stringBuilder.append(Constants.lineSeparator);
		}
		return stringBuilder.toString();
	}

	public String printTracks () {
		StringBuilder stringBuilder = new StringBuilder();
		for (Track track : getAllTracks()) {
			stringBuilder.append(track.toString());
			stringBuilder.append(Constants.lineSeparator);
		}
		return stringBuilder.toString();
	}

	public String printArtists () {
		StringBuilder stringBuilder = new StringBuilder();
		for (Artist artist : getAllArtists()) {
			stringBuilder.append(artist.toString());
			stringBuilder.append(Constants.lineSeparator);
		}
		return stringBuilder.toString();
	}

	public String getAlbumDetails (String id) {
		return getAlbum(id).getDetails();
	}

	public String getTrackDetails (String id) {
		return getTrack(id).getDetails();
	}

	public String getArtistDetails (String id) {
		return getArtist(id).getDetails();
	}
}
