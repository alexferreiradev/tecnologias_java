package dev.alexferreira.sampleapi.domain.inquilino;

public interface ImagemInquilinoStorage {

   String save(Inquilino inquilino, byte[] image);

   byte[] get(Inquilino inquilino);

}
