package app.Util;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Util {
	/**
	 * Metodo para concatenar dois arrays do mesmo tipo
	 * @param <T> Tipo de ambos os arrays que serao concatenados
	 * @param array1
	 * @param array2
	 * @return array resultante da mesclagem de array1 e array2
	 */
	public static <T> T[] concatenarArrays(T[] array1, T[] array2) {
		T[] result = Arrays.copyOf(array1, array1.length + array2.length);
	    System.arraycopy(array2, 0, result, array1.length, array2.length);
	    return result;
	}
	
	/**
	 * Metodo para obter um array de Field que representam as propriedades da classe informada.
	 * Serao obtidas todas as propriedades da classe e de suas superclasses. 
	 * @param classe classe para obter as propriedades
	 * @return array de Field com as propriedades da classe
	 */
	public static Field[] obterPropriedades(Class<?> classe) {
		if (classe == null)
			return new Field[0];
		
		Field[] campos = classe.getDeclaredFields();
		Field[] camposClasseBase = Util.obterPropriedades(classe.getSuperclass());
		
		campos = Util.concatenarArrays(camposClasseBase, campos);
		
		return campos;
	}
}
