    
SELECT 
    PEDIDOS.COD_PEDIDO pedido, 
    
    PEDIDOS.EMPRESA cod_remetente, 
    EMPRESAS.NOME_FANTASIA nome_empresa_remetente, 
    EMPRESAS.RAZAO_SOCIAL razao_social_remetente,
    EMPRESAS.CNPJ cnpj_remetente,
    EMPRESAS.TELEFONE telefone_remetente,
    EMPRESAS.EMAIL email_remetente,
    EMPRESAS.CAIXA_TERMICA caixa_termica_remetente,
    EMPRESAS.USUARIO usuario_remetente,
    USUARIOREM.LOGIN login_remetente,
    USUARIOREM.SENHA senha_remetente,
    USUARIOREM.STATUS status_usuario_remetente,
    
    -- Dados do remetente / empresa
    ENDERREM.COD_ENDERECO cod_endereco_remetente,
    ENDERREM.RUA rua_remetente,
    ENDERREM.NUMERO numero_remetente,
    ENDERREM.COMPLEMENTO complemento_remetente,
    ENDERREM.BAIRRO bairro_remetente,
    ENDERREM.CIDADE cod_cidade_remetente,
    CIDADEREM.NOME_CIDADE nome_cidade_remetente, 
    CIDADEREM.ESTADO cod_estado_remetente,
    ESTADOREM.NOME_ESTADO nome_estado_remetente,
    ESTADOREM.UF uf_estado_remetente, 
    
    -- Dados do destinatario
    PEDIDOS.DESTINATARIO cod_destinatario,
    DESTINATARIOS.NOME_DEST nome_desstinatario,
    ENDERDEST.COD_ENDERECO cod_endereco_destinatario,
    ENDERDEST.RUA rua_destinatario,
    ENDERDEST.NUMERO numero_destinatario,
    ENDERDEST.COMPLEMENTO complemento_destinatario,
    ENDERDEST.BAIRRO bairro_destinatario,
    ENDERDEST.CIDADE cod_cidade_destinatario,
    CIDADEDEST.NOME_CIDADE nome_cidade_destinatario, 
    CIDADEDEST.ESTADO cod_estado_destinatrio,
    ESTADODEST.NOME_ESTADO nome_estado_destinatario,
    ESTADODEST.UF uf_estado_destinatario, 
    
    
    -- dados dos entregaodres
    PEDIDOS.ENTREGADORES cod_entregador,
    ENTREGADORES.NOME_ENT nome_entregador,
    ENTREGADORES.SOBRENOME_ENT sobrenome_entregador,
    ENTREGADORES.CPF_ENT cpf_entregador,
    USUARIOENTREG.LOGIN login_entregador,
    USUARIOENTREG.SENHA senha_entregador,
    USUARIOENTREG.STATUS status_usuario_entregador,
    
    ENDERENTREG.COD_ENDERECO cod_endereco_entregador,
    ENDERENTREG.RUA rua_entregador,
    ENDERENTREG.NUMERO numero_entregador,
    ENDERENTREG.COMPLEMENTO complemento_entregador,
    ENDERENTREG.BAIRRO bairro_entregador,
    ENDERENTREG.CIDADE cod_cidade_entregador,
    CIDADEENTREG.NOME_CIDADE nome_cidade_entregador, 
    CIDADEENTREG.ESTADO cod_estado_entregador,
    ESTADOENTREG.NOME_ESTADO nome_estado_entregador,
    ESTADOENTREG.UF uf_estado_entregador, 
    
    PEDIDOS.DESCRICAO descricao_pedido,
    PEDIDOS.TIPO_SERVICO servico,
    SERVICO.DESCRICAO descricao_servico,
    SERVICO.TAXA taxa_servico, 
    PEDIDOS.TAXA_EXTRA taxa_extra_pedido,
    PEDIDOS.VALOR_TOTAL valor_total_pedido,
    TO_CHAR(PEDIDOS.DATA_PEDIDO, 'DD/MM/YYYY') emissao
    
FROM 
    PEDIDOS 
    
INNER JOIN ENTREGADORES  ON
    ENTREGADORES.COD_ENT = PEDIDOS.ENTREGADORES
    
INNER JOIN EMPRESAS ON 
    EMPRESAS.COD_EMPRESA = PEDIDOS.EMPRESA
    
INNER JOIN DESTINATARIOS ON
    DESTINATARIOS.COD_DEST = PEDIDOS.DESTINATARIO
    
INNER JOIN ENDERECO ENDERDEST ON
    ENDERDEST.COD_ENDERECO = DESTINATARIOS.ENDERECO_DEST
    
INNER JOIN CIDADES CIDADEDEST ON
    CIDADEDEST.COD_CIDADE = ENDERDEST.CIDADE 
    
INNER JOIN ESTADOS ESTADODEST ON 
    ESTADODEST.COD_ESTADO = CIDADEDEST.ESTADO 
        
INNER JOIN ENDERECO ENDERREM ON
    ENDERREM.COD_ENDERECO = EMPRESAS.ENDERECO_EMPRESA
    
INNER JOIN CIDADES CIDADEREM ON
    CIDADEREM.COD_CIDADE = ENDERREM.CIDADE 
    
INNER JOIN ESTADOS ESTADOREM ON 
    ESTADOREM.COD_ESTADO = CIDADEREM.ESTADO 
    
INNER JOIN ENDERECO ENDERENTREG ON
    ENDERENTREG.COD_ENDERECO = ENTREGADORES.ENDERECO_ENT
    
INNER JOIN CIDADES CIDADEENTREG ON
    CIDADEENTREG.COD_CIDADE = ENDERENTREG.CIDADE 
    
INNER JOIN ESTADOS ESTADOENTREG ON 
    ESTADOENTREG.COD_ESTADO = CIDADEREM.ESTADO     
        
INNER JOIN TIPO_SERVICO SERVICO ON
    SERVICO.COD_SERVICO = PEDIDOS.TIPO_SERVICO   
    
INNER JOIN USUARIOS USUARIOREM ON
    USUARIOREM.COD_USUARIO = EMPRESAS.USUARIO
    
INNER JOIN USUARIOS USUARIOENTREG ON
    USUARIOENTREG.COD_USUARIO = ENTREGADORES.USUARIO
    