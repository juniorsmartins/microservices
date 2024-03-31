CREATE TABLE IF NOT EXISTS empresas(
    empresa_id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    created_at timestamptz NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at timestamptz DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
)

