<#macro naturalPerson person>
    <#include "naturalPersonNames.ftl" />
    <#include "naturalPersonId.ftl" />
    <#escape x as x?html>

        <#if person.title?? >
            <div>
                <b><@label "person.title.label"></@label>:</b>
                ${person.title}
            </div>
        </#if>

        <@naturalPersonNames person />
        <@naturalPersonId person />

        <#if person.humanitarianStatus?? && person.humanitarianStatus.name??>
            <div>
                <b><@label "person.humanitarianStatus.label"></@label>:</b>
                ${person.humanitarianStatus.name}
            </div>
        </#if>
        <#if person.dateOfBirth??>
            <div>
                <b><@label "person.dateOfBirth.label"></@label>:</b>
                ${(person.dateOfBirth).format("dd.MM.yyyy")}
            </div>
        </#if>
        <#if person.birthCountry?? && person.birthCountry.name??>
            <div>
                <b><@label "person.birthCountry.label"></@label>:</b>
                ${person.birthCountry.name}
            </div>
        </#if>
        <#if person.birthSettlement?? && person.birthSettlement.fullSettlementName??>
            <div>
                <b><@label "person.birthSettlement.label"></@label>:</b>
                ${person.birthSettlement.fullSettlementName}
            </div>
        </#if>
        <#if person.citizenship?? && person.citizenship.name??>
            <div>
                <b><@label "person.citizenship.label"></@label>:</b>
                ${person.citizenship.name}
            </div>
        </#if>
        <#if person.email??>
            <div>
                <b><@label "person.email.label"></@label>:</b>
                ${person.email}
            </div>
        </#if>
    </#escape>
</#macro>