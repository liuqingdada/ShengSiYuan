// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Person.proto

package com.shengsiyuan.proto;

/**
 * Protobuf type {@code com.shengsiyuan.proto.StreamListResponse}
 */
public  final class StreamListResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.shengsiyuan.proto.StreamListResponse)
    StreamListResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StreamListResponse.newBuilder() to construct.
  private StreamListResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StreamListResponse() {
    streamResponse_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StreamListResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              streamResponse_ = new java.util.ArrayList<com.shengsiyuan.proto.StreamResponse>();
              mutable_bitField0_ |= 0x00000001;
            }
            streamResponse_.add(
                input.readMessage(com.shengsiyuan.proto.StreamResponse.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        streamResponse_ = java.util.Collections.unmodifiableList(streamResponse_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.shengsiyuan.proto.PersonProto.internal_static_com_shengsiyuan_proto_StreamListResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.shengsiyuan.proto.PersonProto.internal_static_com_shengsiyuan_proto_StreamListResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.shengsiyuan.proto.StreamListResponse.class, com.shengsiyuan.proto.StreamListResponse.Builder.class);
  }

  public static final int STREAMRESPONSE_FIELD_NUMBER = 1;
  private java.util.List<com.shengsiyuan.proto.StreamResponse> streamResponse_;
  /**
   * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
   */
  public java.util.List<com.shengsiyuan.proto.StreamResponse> getStreamResponseList() {
    return streamResponse_;
  }
  /**
   * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
   */
  public java.util.List<? extends com.shengsiyuan.proto.StreamResponseOrBuilder> 
      getStreamResponseOrBuilderList() {
    return streamResponse_;
  }
  /**
   * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
   */
  public int getStreamResponseCount() {
    return streamResponse_.size();
  }
  /**
   * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
   */
  public com.shengsiyuan.proto.StreamResponse getStreamResponse(int index) {
    return streamResponse_.get(index);
  }
  /**
   * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
   */
  public com.shengsiyuan.proto.StreamResponseOrBuilder getStreamResponseOrBuilder(
      int index) {
    return streamResponse_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < streamResponse_.size(); i++) {
      output.writeMessage(1, streamResponse_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < streamResponse_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, streamResponse_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.shengsiyuan.proto.StreamListResponse)) {
      return super.equals(obj);
    }
    com.shengsiyuan.proto.StreamListResponse other = (com.shengsiyuan.proto.StreamListResponse) obj;

    boolean result = true;
    result = result && getStreamResponseList()
        .equals(other.getStreamResponseList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getStreamResponseCount() > 0) {
      hash = (37 * hash) + STREAMRESPONSE_FIELD_NUMBER;
      hash = (53 * hash) + getStreamResponseList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.shengsiyuan.proto.StreamListResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.shengsiyuan.proto.StreamListResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.shengsiyuan.proto.StreamListResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.shengsiyuan.proto.StreamListResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.shengsiyuan.proto.StreamListResponse)
      com.shengsiyuan.proto.StreamListResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.shengsiyuan.proto.PersonProto.internal_static_com_shengsiyuan_proto_StreamListResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.shengsiyuan.proto.PersonProto.internal_static_com_shengsiyuan_proto_StreamListResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.shengsiyuan.proto.StreamListResponse.class, com.shengsiyuan.proto.StreamListResponse.Builder.class);
    }

    // Construct using com.shengsiyuan.proto.StreamListResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getStreamResponseFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (streamResponseBuilder_ == null) {
        streamResponse_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        streamResponseBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.shengsiyuan.proto.PersonProto.internal_static_com_shengsiyuan_proto_StreamListResponse_descriptor;
    }

    public com.shengsiyuan.proto.StreamListResponse getDefaultInstanceForType() {
      return com.shengsiyuan.proto.StreamListResponse.getDefaultInstance();
    }

    public com.shengsiyuan.proto.StreamListResponse build() {
      com.shengsiyuan.proto.StreamListResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.shengsiyuan.proto.StreamListResponse buildPartial() {
      com.shengsiyuan.proto.StreamListResponse result = new com.shengsiyuan.proto.StreamListResponse(this);
      int from_bitField0_ = bitField0_;
      if (streamResponseBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          streamResponse_ = java.util.Collections.unmodifiableList(streamResponse_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.streamResponse_ = streamResponse_;
      } else {
        result.streamResponse_ = streamResponseBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.shengsiyuan.proto.StreamListResponse) {
        return mergeFrom((com.shengsiyuan.proto.StreamListResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.shengsiyuan.proto.StreamListResponse other) {
      if (other == com.shengsiyuan.proto.StreamListResponse.getDefaultInstance()) return this;
      if (streamResponseBuilder_ == null) {
        if (!other.streamResponse_.isEmpty()) {
          if (streamResponse_.isEmpty()) {
            streamResponse_ = other.streamResponse_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureStreamResponseIsMutable();
            streamResponse_.addAll(other.streamResponse_);
          }
          onChanged();
        }
      } else {
        if (!other.streamResponse_.isEmpty()) {
          if (streamResponseBuilder_.isEmpty()) {
            streamResponseBuilder_.dispose();
            streamResponseBuilder_ = null;
            streamResponse_ = other.streamResponse_;
            bitField0_ = (bitField0_ & ~0x00000001);
            streamResponseBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getStreamResponseFieldBuilder() : null;
          } else {
            streamResponseBuilder_.addAllMessages(other.streamResponse_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.shengsiyuan.proto.StreamListResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.shengsiyuan.proto.StreamListResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.shengsiyuan.proto.StreamResponse> streamResponse_ =
      java.util.Collections.emptyList();
    private void ensureStreamResponseIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        streamResponse_ = new java.util.ArrayList<com.shengsiyuan.proto.StreamResponse>(streamResponse_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.shengsiyuan.proto.StreamResponse, com.shengsiyuan.proto.StreamResponse.Builder, com.shengsiyuan.proto.StreamResponseOrBuilder> streamResponseBuilder_;

    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public java.util.List<com.shengsiyuan.proto.StreamResponse> getStreamResponseList() {
      if (streamResponseBuilder_ == null) {
        return java.util.Collections.unmodifiableList(streamResponse_);
      } else {
        return streamResponseBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public int getStreamResponseCount() {
      if (streamResponseBuilder_ == null) {
        return streamResponse_.size();
      } else {
        return streamResponseBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public com.shengsiyuan.proto.StreamResponse getStreamResponse(int index) {
      if (streamResponseBuilder_ == null) {
        return streamResponse_.get(index);
      } else {
        return streamResponseBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public Builder setStreamResponse(
        int index, com.shengsiyuan.proto.StreamResponse value) {
      if (streamResponseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStreamResponseIsMutable();
        streamResponse_.set(index, value);
        onChanged();
      } else {
        streamResponseBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public Builder setStreamResponse(
        int index, com.shengsiyuan.proto.StreamResponse.Builder builderForValue) {
      if (streamResponseBuilder_ == null) {
        ensureStreamResponseIsMutable();
        streamResponse_.set(index, builderForValue.build());
        onChanged();
      } else {
        streamResponseBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public Builder addStreamResponse(com.shengsiyuan.proto.StreamResponse value) {
      if (streamResponseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStreamResponseIsMutable();
        streamResponse_.add(value);
        onChanged();
      } else {
        streamResponseBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public Builder addStreamResponse(
        int index, com.shengsiyuan.proto.StreamResponse value) {
      if (streamResponseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStreamResponseIsMutable();
        streamResponse_.add(index, value);
        onChanged();
      } else {
        streamResponseBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public Builder addStreamResponse(
        com.shengsiyuan.proto.StreamResponse.Builder builderForValue) {
      if (streamResponseBuilder_ == null) {
        ensureStreamResponseIsMutable();
        streamResponse_.add(builderForValue.build());
        onChanged();
      } else {
        streamResponseBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public Builder addStreamResponse(
        int index, com.shengsiyuan.proto.StreamResponse.Builder builderForValue) {
      if (streamResponseBuilder_ == null) {
        ensureStreamResponseIsMutable();
        streamResponse_.add(index, builderForValue.build());
        onChanged();
      } else {
        streamResponseBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public Builder addAllStreamResponse(
        java.lang.Iterable<? extends com.shengsiyuan.proto.StreamResponse> values) {
      if (streamResponseBuilder_ == null) {
        ensureStreamResponseIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, streamResponse_);
        onChanged();
      } else {
        streamResponseBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public Builder clearStreamResponse() {
      if (streamResponseBuilder_ == null) {
        streamResponse_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        streamResponseBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public Builder removeStreamResponse(int index) {
      if (streamResponseBuilder_ == null) {
        ensureStreamResponseIsMutable();
        streamResponse_.remove(index);
        onChanged();
      } else {
        streamResponseBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public com.shengsiyuan.proto.StreamResponse.Builder getStreamResponseBuilder(
        int index) {
      return getStreamResponseFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public com.shengsiyuan.proto.StreamResponseOrBuilder getStreamResponseOrBuilder(
        int index) {
      if (streamResponseBuilder_ == null) {
        return streamResponse_.get(index);  } else {
        return streamResponseBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public java.util.List<? extends com.shengsiyuan.proto.StreamResponseOrBuilder> 
         getStreamResponseOrBuilderList() {
      if (streamResponseBuilder_ != null) {
        return streamResponseBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(streamResponse_);
      }
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public com.shengsiyuan.proto.StreamResponse.Builder addStreamResponseBuilder() {
      return getStreamResponseFieldBuilder().addBuilder(
          com.shengsiyuan.proto.StreamResponse.getDefaultInstance());
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public com.shengsiyuan.proto.StreamResponse.Builder addStreamResponseBuilder(
        int index) {
      return getStreamResponseFieldBuilder().addBuilder(
          index, com.shengsiyuan.proto.StreamResponse.getDefaultInstance());
    }
    /**
     * <code>repeated .com.shengsiyuan.proto.StreamResponse streamResponse = 1;</code>
     */
    public java.util.List<com.shengsiyuan.proto.StreamResponse.Builder> 
         getStreamResponseBuilderList() {
      return getStreamResponseFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.shengsiyuan.proto.StreamResponse, com.shengsiyuan.proto.StreamResponse.Builder, com.shengsiyuan.proto.StreamResponseOrBuilder> 
        getStreamResponseFieldBuilder() {
      if (streamResponseBuilder_ == null) {
        streamResponseBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.shengsiyuan.proto.StreamResponse, com.shengsiyuan.proto.StreamResponse.Builder, com.shengsiyuan.proto.StreamResponseOrBuilder>(
                streamResponse_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        streamResponse_ = null;
      }
      return streamResponseBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.shengsiyuan.proto.StreamListResponse)
  }

  // @@protoc_insertion_point(class_scope:com.shengsiyuan.proto.StreamListResponse)
  private static final com.shengsiyuan.proto.StreamListResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.shengsiyuan.proto.StreamListResponse();
  }

  public static com.shengsiyuan.proto.StreamListResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StreamListResponse>
      PARSER = new com.google.protobuf.AbstractParser<StreamListResponse>() {
    public StreamListResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StreamListResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StreamListResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StreamListResponse> getParserForType() {
    return PARSER;
  }

  public com.shengsiyuan.proto.StreamListResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

