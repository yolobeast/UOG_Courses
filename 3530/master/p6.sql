DROP FUNCTION q06();
CREATE OR REPLACE FUNCTION q06() RETURNS void as $$
    declare
            V_Vname TEXT;
            V_Vbalance DECIMAL(20,2);
            
            c2 cursor for SELECT Vname, Vbalance FROM vendor;
    begin
        raise notice '';
        open c2;
        loop
            fetch c2 into V_Vname, V_Vbalance;
            exit when not found;
            UPDATE vendor SET Vbalance = Vbalance - (Vbalance * 0.04) WHERE Vname = V_Vname;
            raise notice 'Vendor Name: %', V_Vname;
            raise notice 'Fee Charged: %', (V_Vbalance * 0.04);
            raise notice 'New Balance: %', (V_Vbalance - (V_Vbalance * 0.04));
            raise notice '';

        end loop;
        close c2;
    end;
$$ language plpgsql;
