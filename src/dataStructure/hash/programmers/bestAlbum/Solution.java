// 베스트 엘범
// https://school.programmers.co.kr/learn/courses/30/lessons/42579
// level 3
package dataStructure.hash.programmers.bestAlbum;

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Album> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            Album album = map.get(genre);
            if (album == null) {
                album = new Album();
                map.put(genre, album);
            }
            album.add(play);
            album.add(new Song(i, play));
        }

        List<Album> albums = new ArrayList<>(map.values());
        albums.sort((a, b) -> Integer.compare(b.totalPlayTime, a.totalPlayTime));

        List<Integer> answer = new ArrayList<>();
        for (Album album : albums) {
            album.songs.sort(null);
            answer.add(album.songs.get(0).index);
            if (album.songs.size() > 1)
                answer.add(album.songs.get(1).index);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Album {
    int totalPlayTime;
    List<Song> songs;

    public Album() {
        this.totalPlayTime = 0;
        this.songs = new ArrayList<>();
    }

    public void add(int time) {
        totalPlayTime += time;
    }

    public void add(Song song) {
        songs.add(song);
    }
}

class Song implements Comparable<Song> {
    int index;
    int playTime;

    public Song(int index, int playTime) {
        this.index = index;
        this.playTime = playTime;
    }

    @Override
    public int compareTo(Song song) {
        if (this.playTime == song.playTime)
            return this.index - song.index;
        return song.playTime - this.playTime;
    }
}
