/*
 * Copyright (c) 2017 coodex.org (jujus.shen@126.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.coodex.concrete.accounts.organization.api;

import org.coodex.concrete.accounts.organization.pojo.Position;
import org.coodex.concrete.api.*;
import org.coodex.concrete.api.pojo.StrID;
import org.coodex.concrete.jaxrs.BigString;

import java.util.Set;

import static org.coodex.concrete.accounts.AccountManagementRoles.ORGANIZATION_MANAGER;
import static org.coodex.concrete.accounts.AccountManagementRoles.SYSTEM_MANAGER;
import static org.coodex.concrete.accounts.AccountManagementRoles.TENANT_MANAGER;

/**
 * Created by davidoff shen on 2017-04-28.
 */
@MicroService("positions")
@Abstract
@AccessAllow(roles = {SYSTEM_MANAGER, TENANT_MANAGER, ORGANIZATION_MANAGER})
@Safely
public interface AbstractPositionManagementService<P extends Position> extends ConcreteService {

    @Description(name = "新建职位")
    StrID<P> save(P position, @BigString String belong);

    @Description(name = "修改职位信息")
    void update(String id, P position);

    @Description(name = "变更职位归属")
    @MicroService("{id}/changeTo")
    void updateBelongTo(String id, String belong);

    @Description(name = "调整职位顺序")
    @MicroService("{id}/order")
    void updateOrder(String id, Integer order);

    @Description(name = "删除职位")
    void delete(String id);

    @MicroService("{id}/roles")
    @Description(name = "为职位赋角色", description = "以新角色为准")
    void grantTo(String id, String[] roles);

    @MicroService("{id}/roles")
    @Description(name = "获取职位角色")
    @AccessAllow
    Set<String> roles(String id);

}
