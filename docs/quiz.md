# Quiz — 07:00 a 08:00

Responda por mãos levantadas ou pelo chat. Aproximadamente 15 segundos por pergunta e 15 segundos para correção.

**1. O que inicia uma pipeline automaticamente?**

- A. Um gatilho configurado, como um push.
- B. Baixar o APK de uma execução anterior.

**2. Um teste falhou. O que esperamos deste workflow?**

- A. Continuar e gerar o APK mesmo assim.
- B. Interromper antes da geração do APK e mostrar o erro.

**3. O APK gerado é o quê?**

- A. O código-fonte do aplicativo.
- B. Um artefato da execução.

<details>
<summary>Gabarito e explicação</summary>

1. **A.** O evento configurado dispara a receita. Para push funcionar, o fork deve estar conectado ao Codemagic por webhook.
2. **B.** Neste workflow, os testes vêm antes do build e uma falha interrompe o fluxo.
3. **B.** O APK é um pacote instalável produzido a partir do código.

</details>

Se houver confusão, retome: **o gatilho inicia o workflow, as etapas verificam e compilam o código, e o APK é um dos resultados**. Use essa explicação para conectar o quiz à demonstração.
