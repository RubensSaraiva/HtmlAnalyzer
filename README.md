# Rubens_Eduardo_Freitas_Saraiva

 
# HtmlAnalyzer

O HtmlAnalyzer é um programa em Java que analisa um arquivo HTML ou uma URL e encontra o trecho de texto mais profundo na estrutura HTML. O trecho de texto mais profundo é aquele que está aninhado na maior quantidade de tags HTML.

## Como usar
O programa pode ser executado a partir da linha de comando. É necessário passar como argumento uma URL ou o caminho de um arquivo HTML válido.

Exemplo de uso com uma URL: java HtmlAnalyzer URL

Exemplo de uso com um arquivo HTML local: java HtmlAnalyzer /caminho/para/arquivo.html

## Como funciona
O programa lê o conteúdo HTML a partir da URL ou do arquivo, e então analisa cada linha do código em busca de tags HTML e trechos de texto. Para encontrar o trecho de texto mais profundo, o programa conta quantos níveis de aninhamento existem na estrutura HTML, e verifica qual trecho de texto está no nível mais profundo.
Se a estrutura HTML estiver mal formada, o programa exibe uma mensagem de erro.

## Limitações
O programa não é capaz de analisar páginas HTML dinâmicas que são geradas a partir de scripts do lado do cliente, como JavaScript. Além disso, o programa assume que a estrutura HTML está bem formada e pode apresentar resultados imprecisos se isso não for verdade.
