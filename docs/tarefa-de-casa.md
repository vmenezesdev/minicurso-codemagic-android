# Tarefa de casa — seu código, sua pipeline, seu APK

Objetivo: personalizar o app no seu fork, observar uma falha de teste impedir o build e gerar um APK após corrigir o erro. Reserve cerca de 20 a 30 minutos, além do tempo de fila e compilação.

## 1. Prepare sua cópia

1. Abra o [repositório do curso](https://github.com/vmenezesdev/minicurso-codemagic-android).
2. Dê uma **Star** para marcar o material.
3. Clique em **Fork → Create fork**.
4. Conecte **seu fork** ao Codemagic seguindo o [Comece por aqui](../README.md#comece-por-aqui).
5. Faça uma execução inicial do workflow `android-debug` e confira os quatro testes e o APK.

Você pode fazer as alterações pelo editor de arquivos do GitHub. Android Studio e instalação local são opcionais.

## 2. Personalize o aplicativo

No seu fork, edite [strings.xml](../app/src/main/res/values/strings.xml). Troque apenas o conteúdo da string `welcome_message` por uma mensagem sua. Preserve o nome da string e a estrutura XML.

Exemplo:

```xml
<string name="welcome_message">Meu APK passou pela pipeline!</string>
```

Commite a alteração no fork. Observe o build automático; se não iniciar, confira o webhook ou dispare manualmente selecionando a mesma branch e o novo commit.

## 3. Provoque uma falha controlada

1. Edite [Counter.java](../app/src/main/java/dev/vmenezes/codemagic/android/Counter.java): em `increment()`, troque `value += 1;` por `value += 2;`.
2. Commite essa alteração **apenas no seu fork** e acompanhe a execução correspondente.
3. Abra a etapa **Executar testes unitários** e o relatório. Localize `incrementsExactlyOnePerClick`: o primeiro clique deveria resultar em `1`, mas resultou em `2`.
4. Confirme que a etapa **Gerar APK de debug** não executou nessa tentativa. Confira o commit da execução para não confundir com um APK antigo.
5. Guarde um print do erro. A falha é parte da atividade.

## 4. Corrija e gere o APK

1. Volte para `value += 1;` e commite a correção.
2. **Preserve os testes e sua ordem antes do build** no `codemagic.yaml`; não use opções para ignorar falhas.
3. Na nova execução, confirme que os quatro testes passaram e que o APK foi gerado.
4. Baixe `app-debug.apk`. Se tiver um Android ou emulador disponível, instale e confira a mensagem personalizada, o incremento e o reset. A instalação é opcional para a entrega.

O workflow pronto serve como referência. Ao terminar, você deve conseguir apontar onde se definem gatilho, testes, build e artefatos.

## 5. Registre a entrega

Copie o conteúdo de [entrega-modelo.md](entrega-modelo.md) para um arquivo **`ENTREGA.md` na raiz do seu fork**, preencha e adicione um link para ele no seu README. Os links para imagens devem apontar para arquivos ou anexos acessíveis; não use caminhos locais do seu computador.

Inclua:

- Link do fork e os SHAs dos commits de falha e correção.
- Um print da falha e outro da execução bem-sucedida com o APK disponível.
- Uma explicação curta: o que dispara a execução, por que o erro impediu o build e qual é o artefato final.

Se os links do Codemagic exigirem login, os prints permitem verificar a entrega. Não é necessário abrir PR no repositório do curso nem publicar o APK no GitHub.

## Critérios de conclusão

- [ ] Mensagem personalizada no aplicativo.
- [ ] Fork conectado ao Codemagic.
- [ ] Falha intencional registrada e build bloqueado.
- [ ] Correção registrada sem enfraquecer os testes.
- [ ] Quatro testes passando antes da geração do APK.
- [ ] APK baixado e entrega documentada no fork.

**Extra opcional:** adicione uma etapa `./gradlew --no-daemon :app:lintDebug` entre os testes e o build. Explique a diferença entre um teste unitário e análise estática.
