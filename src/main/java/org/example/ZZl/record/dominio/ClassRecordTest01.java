package org.example.ZZl.record.dominio;

public class ClassRecordTest01 {
    public static void main(String[] args) {
        // Criando o objeto Record
        Manga manga = new Manga("One Piece", 1100);

        // Acessando dados (Note que não é getName(), é name())
        System.out.println(manga.name());     // Imprime: One Piece
        System.out.println(manga.episodes()); // Imprime: 1100

        // O toString já vem formatado nativamente
        System.out.println(manga);
        // Imprime algo como: Manga[name=One Piece, episodes=1100]

        // Testando validação (vai lançar NullPointerException)
        // Manga mangaNull = new Manga(null, 10);
    }
}