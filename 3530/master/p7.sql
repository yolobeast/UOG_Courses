DROP FUNCTION q07();
CREATE OR REPLACE FUNCTION q07() RETURNS void as $$
    declare
            C_name TEXT;
            C_CredLim INT;
            C_Cbal DECIMAL(20,2);
            
            c1 cursor for SELECT Cname, Crlimit, Cbalance FROM customer;
    begin
        raise notice '';
        open c1;
        loop
            fetch c1 into C_name, C_CredLim, C_Cbal;
            exit when not found;
            IF (C_Cbal > C_CredLim) THEN
                UPDATE customer SET Cbalance = Cbalance + (Cbalance - Crlimit) * 0.10 WHERE Cname = C_name;
                raise notice 'Customer Name: %', C_name;
                raise notice 'New Balance: %', C_Cbal + (C_Cbal - C_CredLim) * 0.10;
                raise notice '';
            END IF;

        end loop;
        close c1;
    end;
$$ language plpgsql;
