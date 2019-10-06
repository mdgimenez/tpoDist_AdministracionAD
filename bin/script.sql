update unidades set habitado = 'S' 
	where identificador in (select distinct u.identificador 
								from unidades u inner join inquilinos i 
												on u.identificador = i.identificador 
							)
GO

declare @identificador int
declare @contador int

declare habitados cursor 
for select identificador from unidades where habitado = 'N' order by numero

open habitados
select @contador = 1

fetch next from habitados into @identificador
while @@FETCH_STATUS = 0
begin
	update unidades set habitado = 'S' where identificador = @identificador
	fetch next from habitados into @identificador
	SELECT @contador = @contador + 1
	if @contador = 5
	begin
		SELECT @contador = 1
		fetch next from habitados into @identificador
	end
end

close habitados
deallocate habitados
