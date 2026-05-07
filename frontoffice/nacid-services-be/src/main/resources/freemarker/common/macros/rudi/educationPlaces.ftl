<#macro educationPlaces educationPlaces>
    <#escape x as x?html>
        <#if educationPlaces?? && educationPlaces?size &gt; 0>
            <h3><@label "educationPlace.label"></@label></h3>
            <div>
                <b><@label "educationPlace.label"></@label>:</b>
                ${educationPlaces?map(place -> (place.country?? && place.country.name??)?then(place.country.name, "")+ (place.city??)?then(" - "+ place.city, ""))?join("; ")}
            </div>
        </#if>
    </#escape>
</#macro>