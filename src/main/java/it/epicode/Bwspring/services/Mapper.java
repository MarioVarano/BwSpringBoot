package it.epicode.Bwspring.services;

public interface Mapper<D, S> {

	S map(D input);
}
