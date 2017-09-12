DROP FUNCTION q03(C_Account TEXT, C_Cname TEXT, C_Province TEXT, C_Crlimit INT);
CREATE OR REPLACE FUNCTION q03(C_Account TEXT, C_Cname TEXT, C_Province TEXT, C_Crlimit INT) 

RETURNS TABLE(c_Acc TEXT, c_cnames TEXT, C_Provinces TEXT, C_Cbalances DECIMAL(20,2), C_Crlimits INT) as $$
    declare

    begin
            INSERT INTO customer (Account, Cname, Province, Cbalance, Crlimit) VALUES 
                (C_Account, C_Cname, C_Province, 0, C_Crlimit);
            return query
            SELECT * FROM customer;
    end;
$$ language plpgsql;
