/* A Classe TipoToKen é uma classe do tipo enum, no qual os varlores declarados são constantes 
* pré-definidas,ou seja, temos todos os token existes da nossa linguagem para classificarmos no  
* final. 
*/

public enum TipoToken{
  PCDec, PCProg, PCInt, PCReal, PCLer, PCImprimir, PCSe, PCEntao, PCSenao,  PCEnqto, PCIni,       PCFim, OpAritMult, OpAritDiv, OpAritSoma, OpAritSub, OpRelMenor, OpRelMenorIgual,     OpRelMaior,OpRelMaiorIgual, OpRelIgual, OpRelDif, OpBoolE, OpBoolOu, Delim, Atrib,    AbrePar, FechaPar, Var, NumInt, NumReal, Cadeia, ERROR
}