package com.example.profile.readmodels;

import lombok.Builder;

public final class ProfileReadModels {
    private ProfileReadModels() {}

    @Builder
    public record ProfileListViewReadModel() {}

    @Builder
    public record ProfileDetailedViewReadModel(
            String id,
            String accountId,
            String username,
            String birth,
            String gender,
            String nickname
    ) {}


}
